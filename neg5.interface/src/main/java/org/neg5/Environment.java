package org.neg5;

public enum Environment {
    DEV,
    PRODUCTION;

    public static Environment getEnvironment() {
        String env = System.getenv("NEG5_ENVIRONMENT");
        return Environment.valueOf(env);
    }
}
