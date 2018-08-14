package org.neg5.db;

import javax.inject.Singleton;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.EntityTransaction;

@Singleton
public class PersistenceManager {

    private EntityManagerFactory entityManagerFactory;
    private ThreadLocal<EntityManager> entityManagerThreadLocal = new ThreadLocal<>();

    private static final String ENTITY_MANAGER_UNIT = "persistence";

    public EntityManager getEntityManager() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory(ENTITY_MANAGER_UNIT);
        }
        EntityManager em = entityManagerThreadLocal.get();
        if (em == null) {
            entityManagerThreadLocal.set(entityManagerFactory.createEntityManager());
        }
        return entityManagerThreadLocal.get();
    }

    public EntityTransaction getTransaction() {
        return getEntityManager().getTransaction();
    }

    public void begin() {
        EntityTransaction transaction = getEntityManager().getTransaction();
        if (!transaction.isActive()) {
            transaction.begin();
        }
    }

    public void commit() {
        EntityTransaction transaction = getEntityManager().getTransaction();
        if (transaction.isActive()) {
            transaction.commit();
        }
    }

    public void rollback() {
        EntityTransaction transaction = getEntityManager().getTransaction();
        if (transaction.isActive()) {
            transaction.rollback();
        }
    }

    public void close() {
        EntityManager manager = entityManagerThreadLocal.get();
        if (manager != null) {
            manager.clear();
            manager.close();
            entityManagerThreadLocal.remove();
        }
    }
}
