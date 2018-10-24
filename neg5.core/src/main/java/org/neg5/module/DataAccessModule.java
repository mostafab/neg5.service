package org.neg5.module;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.cfg.AvailableSettings;
import org.neg5.core.DAOProvider;
import org.neg5.core.PersistenceManager;
import org.neg5.core.ReadOnly;
import org.neg5.core.ReadWrite;
import org.neg5.core.EntityManagerSupplier;
import org.neg5.core.ThreadLocalPersistenceManager;
import org.neg5.core.TransactionalModule;
import org.neg5.daos.AbstractDAO;
import org.reflections.Reflections;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class DataAccessModule extends AbstractModule {

    private static final String USERNAME_PROP = "neg5.username";
    private static final String PASSWORD_PROP = "neg5.password";
    private static final String JDBC_URL_PROP = "neg5.jdbc_url";
    private static final String CONNECTION_POOL_SIZE_PROP = "neg5.maxPoolSize";

    private static final String DRIVER_CLASS_NAME = "org.postgresql.Driver";

    private static final String PERSISTENCE_UNIT_NAME = "org.neg5.data";

    private final EntityManagerFactory readOnlyEntityManagerFactory;
    private final EntityManagerFactory readWriteEntityManagerFactory;
    private final PersistenceManager persistenceManager;

    public DataAccessModule() {
        this.readOnlyEntityManagerFactory = provideROEntityManagerFactory();
        this.readWriteEntityManagerFactory = provideRWEntityManagerFactory();

        persistenceManager =
                new ThreadLocalPersistenceManager(readWriteEntityManagerFactory, readOnlyEntityManagerFactory);
    }

    @Override
    protected void configure() {
        bind(PersistenceManager.class).toInstance(persistenceManager);
        install(new TransactionalModule());
        bindDAOs();
    }

    private void bindDAOs() {
        Reflections reflections = new Reflections("org.neg5.daos");
        reflections.getSubTypesOf(AbstractDAO.class).forEach(this::bindDAO);
    }

    @SuppressWarnings("unchecked")
    private <T extends AbstractDAO> void bindDAO(Class<T> daoClass) {
        if (!Modifier.isAbstract(daoClass.getModifiers())) {
            bind(daoClass).annotatedWith(ReadOnly.class)
                    .toProvider(new DAOProvider(getProvider(daoClass),
                            new EntityManagerSupplier(true, persistenceManager)))
                    .in(Scopes.SINGLETON);
            bind(daoClass).annotatedWith(ReadWrite.class)
                    .toProvider(new DAOProvider(getProvider(daoClass),
                            new EntityManagerSupplier(false, persistenceManager)))
                    .in(Scopes.SINGLETON);
        }
    }

    private EntityManagerFactory provideRWEntityManagerFactory() {
        DataSource dataSource = getDataSource(new EnvironmentBackedSystemVariables(), false);
        Map<String, Object> properties = new HashMap<>();
        properties.put(AvailableSettings.DATASOURCE, dataSource);
        return Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME, properties);
    }

    private EntityManagerFactory provideROEntityManagerFactory() {
        DataSource dataSource = getDataSource(new EnvironmentBackedSystemVariables(), true);
        Map<String, Object> properties = new HashMap<>();
        properties.put(AvailableSettings.DATASOURCE, dataSource);
        return Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME, properties);
    }

    private DataSource getDataSource(SystemProperties properties, boolean readOnly) {
        HikariConfig config = new HikariConfig();
        config.setUsername(properties.getString(USERNAME_PROP));
        config.setPassword(properties.getString(PASSWORD_PROP));
        config.setJdbcUrl(properties.getString(JDBC_URL_PROP));
        config.setDriverClassName(DRIVER_CLASS_NAME);
        config.setMaximumPoolSize(properties.getInt(CONNECTION_POOL_SIZE_PROP));
        config.setReadOnly(readOnly);

        return new HikariDataSource(config);
    }
}
