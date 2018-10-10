package org.neg5.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import org.apache.commons.dbcp.BasicDataSource;
import org.neg5.core.TransactionalSimpleModule;
import org.neg5.db.PersistenceManager;
import org.neg5.db.ThreadLocalPersistenceManager;

import javax.sql.DataSource;

public class DataAccessModule extends AbstractModule {

    public static final String DATA_SOURCE_PROP_NAME = "DataSource";

    private static final String USERNAME_PROP = "neg5.username";
    private static final String PASSWORD_PROP = "neg5.password";
    private static final String JDBC_URL_PROP = "neg5.jdbc_url";

    private static final String DRIVER_CLASS_NAME = "org.postgresql.Driver";

    protected void configure() {
        install(new TransactionalSimpleModule());
        bind(PersistenceManager.class).to(ThreadLocalPersistenceManager.class);
    }

    @Provides
    @Named(DATA_SOURCE_PROP_NAME)
    protected DataSource provideDataSource(
            @Named(SystemPropertiesModule.SYSTEM_PROPS_NAME) SystemProperties properties
    ) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUsername(properties.get(USERNAME_PROP));
        dataSource.setPassword(properties.get(PASSWORD_PROP));
        dataSource.setUrl(properties.get(JDBC_URL_PROP));
        dataSource.setDriverClassName(DRIVER_CLASS_NAME);
        return dataSource;
    }
}
