package org.neg5.module;

import com.google.inject.Singleton;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Singleton
public final class EnvironmentBackedConfig implements Configuration {

    private final Map<String, String> properties;

    public EnvironmentBackedConfig() {
        this.properties = Collections.unmodifiableMap(new HashMap<>(System.getenv()));
    }

    @Override
    public String getString(String key) {
        return properties.get(key);
    }

    @Override
    public Integer getInt(String key) {
        if (!properties.containsKey(key)) {
            return null;
        }
        return Integer.parseInt(properties.get(key));
    }
}
