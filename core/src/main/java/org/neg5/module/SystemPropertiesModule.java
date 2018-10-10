package org.neg5.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;

public class SystemPropertiesModule extends AbstractModule {

    public static final String SYSTEM_PROPS_NAME = "SystemProps";

    protected void configure() {
        bind(SystemProperties.class).to(EnvironmentBackedSystemVariables.class);
    }

    @Provides
    @Named(SYSTEM_PROPS_NAME)
    protected SystemProperties provideSystemProperties() {
        return new EnvironmentBackedSystemVariables();
    }

}
