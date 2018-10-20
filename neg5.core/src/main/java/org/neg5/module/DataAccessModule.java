package org.neg5.module;

import com.google.inject.AbstractModule;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.cfg.AvailableSettings;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class DataAccessModule extends AbstractModule {

    private static final String USERNAME_PROP = "neg5.username";
    private static final String PASSWORD_PROP = "neg5.password";
    private static final String JDBC_URL_PROP = "neg5.jdbc_url";

    private static final String DRIVER_CLASS_NAME = "org.postgresql.Driver";

    private static final String PERSISTENCE_UNIT_NAME = "org.neg5.data";

    @Override
    protected void configure() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(AvailableSettings.DATASOURCE, provideRWDataSource(new EnvironmentBackedSystemVariables()));
        install(new JpaPersistModule(PERSISTENCE_UNIT_NAME).properties(properties));
    }

    private DataSource provideRWDataSource(SystemProperties properties) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setUsername(properties.getString(USERNAME_PROP));
        dataSource.setPassword(properties.getString(PASSWORD_PROP));
        dataSource.setJdbcUrl(properties.getString(JDBC_URL_PROP));
        dataSource.setDriverClassName(DRIVER_CLASS_NAME);
        return dataSource;
    }
}
