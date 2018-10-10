package org.neg5.module;

import com.google.inject.Singleton;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Singleton
public final class EnvironmentBackedSystemVariables implements SystemProperties {

    private final Map<String, String> properties;

    EnvironmentBackedSystemVariables() {
        this.properties = Collections.unmodifiableMap(new HashMap<>(System.getenv()));
    }

    @Override
    public String get(String key) {
        return properties.get(key);
    }
}
