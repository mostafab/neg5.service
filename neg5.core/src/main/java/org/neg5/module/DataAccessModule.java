package org.neg5.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.zaxxer.hikari.HikariDataSource;
import org.neg5.core.TransactionalSimpleModule;
import org.neg5.db.PersistenceManager;
import org.neg5.db.ThreadLocalPersistenceManager;

import javax.sql.DataSource;

public class DataAccessModule extends AbstractModule {

    public static final String READ_WRITE_DATA_SOURCE_PROP_NAME = "DataSource.readWrite";

    private static final String USERNAME_PROP = "neg5.username";
    private static final String PASSWORD_PROP = "neg5.password";
    private static final String JDBC_URL_PROP = "neg5.jdbc_url";

    private static final String DRIVER_CLASS_NAME = "org.postgresql.Driver";

    protected void configure() {
        install(new TransactionalSimpleModule());
        bind(PersistenceManager.class).to(ThreadLocalPersistenceManager.class);
    }

    @Provides
    @Named(READ_WRITE_DATA_SOURCE_PROP_NAME)
    protected DataSource provideRWDataSource(SystemProperties properties) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setUsername(properties.getString(USERNAME_PROP));
        dataSource.setPassword(properties.getString(PASSWORD_PROP));
        dataSource.setJdbcUrl(properties.getString(JDBC_URL_PROP));
        dataSource.setDriverClassName(DRIVER_CLASS_NAME);
        return dataSource;
    }
}
