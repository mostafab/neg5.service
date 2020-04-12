package org.neg5.core;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Deprecated
public class ThreadLocalPersistenceManager implements PersistenceManager {

    private final EntityManagerFactory readWriteEntityManagerFactory;
    private final EntityManagerFactory readOnlyEntityManagerFactory;

    private ThreadLocal<EntityManager> rwEntityManagerThreadLocal = new ThreadLocal<>();
    private ThreadLocal<EntityManager> roEntityManagerThreadLocal = new ThreadLocal<>();

    public ThreadLocalPersistenceManager(
           EntityManagerFactory rwEntityManagerFactory,
           EntityManagerFactory roEntityManagerFactory
    ) {
        readWriteEntityManagerFactory = rwEntityManagerFactory;
        readOnlyEntityManagerFactory = roEntityManagerFactory;
    }

    @Override
    public EntityManager getCurrentEntityManager(boolean readOnly) {
        EntityManager em = getEntityManagerThreadLocal(readOnly).get();
        if (em == null) {
            getEntityManagerThreadLocal(readOnly).set(getEntityManagerFactory(readOnly).createEntityManager());
        }
        return getEntityManagerThreadLocal(readOnly).get();
    }

    @Override
    public void close(boolean readOnly) {
        EntityManager em = getEntityManagerThreadLocal(readOnly).get();
        if (em != null) {
            em.close();
            getEntityManagerThreadLocal(readOnly).remove();
        }
    }

    private EntityManagerFactory getEntityManagerFactory(boolean readOnly) {
        return readOnly
                ? readOnlyEntityManagerFactory
                : readWriteEntityManagerFactory;
    }

    private ThreadLocal<EntityManager> getEntityManagerThreadLocal(boolean readOnly) {
        return readOnly
                ? roEntityManagerThreadLocal
                : rwEntityManagerThreadLocal;
    }
}