package org.neg5.module;

import com.google.inject.AbstractModule;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.cfg.AvailableSettings;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class DataAccessModule extends AbstractModule {

    private static final String USERNAME_PROP = "neg5.username";
    private static final String PASSWORD_PROP = "neg5.password";
    private static final String JDBC_URL_PROP = "neg5.jdbc_url";
    private static final String CONNECTION_POOL_SIZE_PROP = "neg5.maxPoolSize";

    private static final String DRIVER_CLASS_NAME = "org.postgresql.Driver";

    private static final String PERSISTENCE_UNIT_NAME = "org.neg5.data";

    @Override
    protected void configure() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(AvailableSettings.DATASOURCE, provideRWDataSource(new EnvironmentBackedSystemVariables()));
        install(new JpaPersistModule(PERSISTENCE_UNIT_NAME).properties(properties));
    }

    private DataSource provideRWDataSource(SystemProperties properties) {
        HikariConfig config = new HikariConfig();
        config.setUsername(properties.getString(USERNAME_PROP));
        config.setPassword(properties.getString(PASSWORD_PROP));
        config.setJdbcUrl(properties.getString(JDBC_URL_PROP));
        config.setDriverClassName(DRIVER_CLASS_NAME);
        config.setMaximumPoolSize(properties.getInt(CONNECTION_POOL_SIZE_PROP));
        config.setReadOnly(false);

        return new HikariDataSource(config);
    }
}
