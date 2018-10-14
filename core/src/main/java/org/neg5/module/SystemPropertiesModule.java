package org.neg5.module;

import com.google.inject.AbstractModule;

public class SystemPropertiesModule extends AbstractModule {

    protected void configure() {
        bind(SystemProperties.class).toInstance(new EnvironmentBackedSystemVariables());
    }

}
