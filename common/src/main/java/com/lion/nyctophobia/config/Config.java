package com.lion.nyctophobia.config;


/// Based on Omega Config by Draylar: https://github.com/Draylar/omega-config
@SuppressWarnings({"unchecked"})
public interface Config {

    default void save() {
        OmegaConfig.writeConfig((Class<Config>) getClass(), this);
    }

    String getName();

    default String getModid() {
        return null;
    }

    default String getExtension() {
        return "json";
    }

    default String getDirectory() {
        return "";
    }
}

