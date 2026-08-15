package com.lion.nyctophobia.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lion.nyctophobia.Nyctophobia;
import com.lion.nyctophobia.config.annotations.Description;
import com.lion.nyctophobia.platform.ConfigDirectory;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class OmegaConfig {

    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static <T extends Config> T register(Class<T> configClass) {
        try {
            T config = configClass.getDeclaredConstructor().newInstance();

            if (!configExists(config)) {
                config.save();
            } else {
                try {
                    List<String> lines = Files.readAllLines(getConfigPath(config));
                    lines.removeIf(line -> line.trim().startsWith("//"));
                    StringBuilder res = new StringBuilder();
                    lines.forEach(res::append);
                    T object = GSON.fromJson(res.toString(), configClass);

                    object.save();
                    return object;
                } catch (Exception e) {
                    Nyctophobia.LOGGER.error(e.toString());
                    Nyctophobia.LOGGER.info(String.format("Encountered an error while reading %s config, falling back to default values.", config.getName()));
                    Nyctophobia.LOGGER.info(String.format("If this problem persists, delete the config file %s and try again.", config.getName() + "." + config.getExtension()));

                    // The fresh instance carries empty maps until save() fills them. Returning it as
                    // is makes every later getBiome/getFog throw and takes the mod down long after
                    // the actual fault, so rewrite the unreadable file with the defaults.
                    config.save();
                }
            }

            return config;
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                InvocationTargetException exception) {
            exception.printStackTrace();
            throw new RuntimeException("No valid constructor found for: " + configClass.getName());
        }
    }

    public static <T extends Config> void writeConfig(Class<T> configClass, T instance) {
        String json = GSON.toJson(instance);

        List<String> lines = new ArrayList<>(Arrays.asList(json.split("\n")));
        Map<Integer, String> insertions = new TreeMap<>();
        Map<String, String> keyToComments = new HashMap<>();

        for (Field field : configClass.getDeclaredFields()) {
            addFieldComments(field, keyToComments);
        }

        for (Class<?> innerClass : configClass.getDeclaredClasses()) {
            for (Field field : innerClass.getDeclaredFields()) {
                addFieldComments(field, keyToComments);
            }
        }

        for (int i = 0; i < lines.size(); i++) {
            String at = lines.get(i);

            for (Map.Entry<String, String> entry : keyToComments.entrySet()) {
                String key = entry.getKey();
                String comment = entry.getValue();

                if (at.trim().startsWith(String.format("\"%s\"", key))) {
                    insertions.put(i + insertions.size(), commentBlock(comment, getStartingWhitespace(at)));
                    break;
                }
            }
        }

        for (Map.Entry<Integer, String> entry : insertions.entrySet()) {
            Integer key = entry.getKey();
            String value = entry.getValue();
            lines.add(key, value);
        }

        StringBuilder res = new StringBuilder();
        lines.forEach(str -> res.append(String.format("%s%n", str)));

        try {
            Path configPath = getConfigPath(instance);
            configPath.toFile().getParentFile().mkdirs();
            Files.write(configPath, res.toString().getBytes());
        } catch (IOException ioException) {
            Nyctophobia.LOGGER.error(ioException.toString());
            Nyctophobia.LOGGER.info(String.format("Write error, using default values for config %s.", configClass));
        }
    }

    /// Every line needs its own `//`. The reader drops comment lines by that prefix, so a multi-line
    /// description behind a single marker leaves bare prose in the file and the next start fails to
    /// parse its own config.
    private static String commentBlock(String comment, String indent) {
        return Arrays.stream(comment.split("\n"))
                .map(line -> String.format("%s//%s", indent, line))
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private static void addFieldComments(Field field, Map<String, String> keyToComments) {
        String fieldName = field.getName();
        Annotation[] annotations = field.getDeclaredAnnotations();

        // Find comment
        for (Annotation annotation : annotations) {
            if (annotation instanceof Description) {
                keyToComments.put(fieldName, ((Description) annotation).value());
                break;
            }
        }
    }

    private static String getStartingWhitespace(String input) {
        int index = -1;

        char[] chars = input.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char at = chars[i];

            if (at != ' ') {
                index = i;
                break;
            }
        }

        if (index != -1) {
            return input.substring(0, index);
        } else {
            return "";
        }
    }

    public static Path getConfigPath(Config config) {
        return Paths.get(ConfigDirectory.getConfigDirectory().toString(), config.getDirectory(), String.format("%s.%s", config.getName(), config.getExtension()));
    }

    public static boolean configExists(Config config) {
        return Files.exists(getConfigPath(config));
    }
}
