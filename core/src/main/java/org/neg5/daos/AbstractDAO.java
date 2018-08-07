package org.neg5.daos;

import org.hibernate.Session;

public abstract class AbstractDAO<T> {

    private Class<T> persistentClass;

    protected AbstractDAO(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    public T get(String id) {
        return getSession().get(persistentClass, id);
    }

    public T save(T entity) {
        return (T) getSession().save(entity);
    }

    protected Class<T> getPersistentClass() {
        return persistentClass;
    }

    protected Session getSession() {
        return null;
    }
}
