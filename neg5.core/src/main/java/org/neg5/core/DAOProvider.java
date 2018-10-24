package org.neg5.core;

import com.google.inject.Provider;
import org.neg5.daos.AbstractDAO;

public class DAOProvider<T extends AbstractDAO> implements Provider<T> {

    private final Provider<T> provider;
    private final EntityManagerSupplier entityManagerSupplier;

    public DAOProvider(Provider<T> provider, EntityManagerSupplier entityManagerSupplier) {
        this.provider = provider;
        this.entityManagerSupplier = entityManagerSupplier;
    }

    @Override
    public T get() {
        T dao = provider.get();
        dao.setEntityManagerSupplier(entityManagerSupplier);
        return dao;
    }
}
