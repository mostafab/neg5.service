package org.neg5.daos;

import com.google.inject.Inject;
import org.neg5.db.PersistenceManager;

import javax.persistence.EntityManager;

public abstract class AbstractDAO<T> {

    private Class<T> persistentClass;

    @Inject
    private PersistenceManager persistenceManager;

    protected AbstractDAO(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    public T get(String id) {
        return getEntityManager().find(persistentClass, id);
    }

    public T save(T entity) {
        return getEntityManager().merge(entity);
    }

    public void flush() {
        getEntityManager().flush();
    }

    protected Class<T> getPersistentClass() {
        return persistentClass;
    }

    protected EntityManager getEntityManager() {
        return persistenceManager.getEntityManager();
    }
}
