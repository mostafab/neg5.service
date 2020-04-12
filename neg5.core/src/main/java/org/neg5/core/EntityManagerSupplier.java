package org.neg5.core;

import javax.persistence.EntityManager;
import java.util.function.Supplier;

@Deprecated
public class EntityManagerSupplier implements Supplier<EntityManager> {

    private final PersistenceManager persistenceManager;
    private final boolean readOnly;

    public EntityManagerSupplier(boolean readOnly, PersistenceManager persistenceManager) {
        this.readOnly = readOnly;
        this.persistenceManager = persistenceManager;
    }

    @Override
    public EntityManager get() {
        return persistenceManager.getCurrentEntityManager(readOnly);
    }
}
