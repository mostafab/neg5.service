package org.neg5.db;

import com.google.inject.Singleton;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.EntityTransaction;

@Singleton
public class ThreadLocalPersistenceManager implements PersistenceManager {

    private EntityManagerFactory entityManagerFactory;
    private ThreadLocal<EntityManager> entityManagerThreadLocal = new ThreadLocal<>();

    private static final String ENTITY_MANAGER_UNIT = "org.neg5.data";

    protected ThreadLocalPersistenceManager() {
        entityManagerFactory = Persistence.createEntityManagerFactory(ENTITY_MANAGER_UNIT);
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
        if (!transaction.isActive()) {
            transaction.begin();
        }
    }

    @Override
    public void commit() {
        EntityTransaction transaction = getEntityManager().getTransaction();
        if (transaction.isActive()) {
            transaction.commit();
        }
    }

    @Override
    public void rollback() {
        EntityTransaction transaction = getEntityManager().getTransaction();
        if (transaction.isActive()) {
            transaction.rollback();
        }
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
