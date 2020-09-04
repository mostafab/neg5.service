package org.neg5.module;

import com.google.inject.AbstractModule;
import com.google.inject.persist.jpa.JpaPersistModule;
import neg5.db.Neg5DatabaseConfiguration;
import org.hibernate.cfg.AvailableSettings;

import java.util.HashMap;
import java.util.Map;

public class DataAccessModule extends AbstractModule {

    private static final String PERSISTENCE_UNIT_NAME = "org.neg5.data";

    public DataAccessModule() {}

    @Override
    protected void configure() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(AvailableSettings.DATASOURCE, Neg5DatabaseConfiguration.getDataSource());
        install(new JpaPersistModule(PERSISTENCE_UNIT_NAME).properties(properties));
    }
}
