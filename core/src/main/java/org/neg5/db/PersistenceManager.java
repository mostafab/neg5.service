package org.neg5.db;

import javax.inject.Singleton;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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

    public void close() {
        EntityManager manager = entityManagerThreadLocal.get();
        if (manager != null) {
            manager.close();
            entityManagerThreadLocal.remove();
        }
    }
}
