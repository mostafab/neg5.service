package org.neg5.managers;

import org.neg5.daos.AbstractDAO;
import org.neg5.data.AbstractDataObject;

import javax.persistence.NoResultException;

public abstract class AbstractManager<T extends AbstractDataObject<T>, DTO> {

    protected abstract AbstractDAO<T> getDAO();

    public T get(String id) {
        T entity = getDAO().get(id);
        if (entity == null) {
            throw new NoResultException("No result found for entity with id " + id);
        }
        return entity.copyOf();
    }
}
