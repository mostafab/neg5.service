package org.neg5.module;

public interface SystemProperties {

    /**
     * Get a property by key and return it as a string
     * @param key the key
     * @return value of key
     */
    String getString(String key);

    /**
     * Get a property by key and return it as an int
     * @param key the key
     * @return value of key as an int
     */
    Integer getInt(String key);
}
