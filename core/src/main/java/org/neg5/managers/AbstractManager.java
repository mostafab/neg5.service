package org.neg5.managers;

import org.neg5.daos.AbstractDAO;

public abstract class AbstractManager<T> {

    protected abstract AbstractDAO<T> getDAO();

    public T get(String id) {
        return getDAO().get(id);
    }
}
