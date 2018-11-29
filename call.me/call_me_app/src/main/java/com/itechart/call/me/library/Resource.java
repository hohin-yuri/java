package com.itechart.call.me.library;

import java.util.ResourceBundle;

public class Resource {
    private static Resource instance;
    private ResourceBundle resourceBundle;
    private static final String BUNDLE_NAME = "com/itechart/call/me/library/resource.config";

    public static Resource getInstance() {
        if (instance == null) {
            instance = new Resource();
            instance.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }

    public String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}