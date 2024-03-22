package com.example.security_study.static_factory;

import java.util.HashMap;
import java.util.Map;

public class Settings {

    private boolean steering;
    private boolean ABS;
    private final Difficulty difficulty;
    private static Map<String, Settings> CACHE = new HashMap<>();

    public static void setCACHE(String key , Settings settings) {
        CACHE.put(key, settings);
    }

    public static Settings getSettings(String key , Settings settings) {
        if (CACHE.containsKey(key)) {
            return CACHE.get(key);
        } else {
            CACHE.put(key, settings);
            return settings;
        }
    }

    public Map<String, Settings> getCacheList() {
        return CACHE;
    }

    private Settings(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public static Settings createSettings(Difficulty difficulty) {
        return new Settings(difficulty);
    }

    public void setSteering(boolean steering) {
        this.steering = steering;
    }

    public void setABS(boolean ABS) {
        this.ABS = ABS;
    }

    //    public static void main(String[] args) {
//        System.out.println( new Settings());
//        System.out.println( new Settings());
//        System.out.println( new Settings());
//    }
//
//    private Settings() {
//    }
//
//    private static final Settings SETTINGS = new Settings();
//    private static final Settings SUB_SETTINGS = new Settings();
//
//    public static Settings newInstance() {
//        return SETTINGS;
//    }
}
