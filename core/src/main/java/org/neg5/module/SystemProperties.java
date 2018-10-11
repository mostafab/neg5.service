package org.neg5.module;

public interface SystemProperties {

    /**
     * Get a property by key and return it as a string
     * @param key the key
     * @return value of key
     */
    String getString(String key);

    /**
     * Get a property by key and return it as a long
     * @param key the key
     * @return value of key as a long
     */
    Long getLong(String key);
}
