package org.neg5.db;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import org.hibernate.cfg.AvailableSettings;
import org.neg5.module.DataAccessModule;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.EntityTransaction;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class ThreadLocalPersistenceManager implements PersistenceManager {

    private EntityManagerFactory entityManagerFactory;
    private ThreadLocal<EntityManager> entityManagerThreadLocal = new ThreadLocal<>();

    private static final String ENTITY_MANAGER_UNIT = "org.neg5.data";

    private final DataSource dataSource;

    @Inject
    protected ThreadLocalPersistenceManager(@Named(DataAccessModule.READ_WRITE_DATA_SOURCE_PROP_NAME) DataSource dataSource) {
        this.dataSource = dataSource;
        Map<String, Object> properties = new HashMap<>();
        properties.put(AvailableSettings.DATASOURCE, dataSource);
        entityManagerFactory = Persistence.createEntityManagerFactory(ENTITY_MANAGER_UNIT, properties);
    }

    @Override
    public EntityManager getEntityManager() {
        EntityManager em = entityManagerThreadLocal.get();
        if (em == null) {
            entityManagerThreadLocal.set(entityManagerFactory.createEntityManager());
        }
        return entityManagerThreadLocal.get();
    }

    @Override
    public EntityTransaction getCurrentTransaction() {
        return getEntityManager().getTransaction();
    }

    @Override
    public void begin() {
        EntityTransaction transaction = getEntityManager().getTransaction();
        transaction.begin();
    }

    @Override
    public void commit() {
        EntityTransaction transaction = getEntityManager().getTransaction();
        transaction.commit();
    }

    @Override
    public void rollback() {
        EntityTransaction transaction = getEntityManager().getTransaction();
        transaction.rollback();
    }

    @Override
    public void close() {
        EntityManager manager = entityManagerThreadLocal.get();
        if (manager != null) {
            manager.clear();
            manager.close();
            entityManagerThreadLocal.remove();
        }
    }
}
