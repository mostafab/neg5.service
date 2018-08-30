package org.neg5.module;

import com.google.inject.AbstractModule;
import org.neg5.core.TransactionalSimpleModule;
import org.neg5.db.PersistenceManager;
import org.neg5.db.ThreadLocalPersistenceManager;

public class DataAccessModule extends AbstractModule {

    protected void configure() {
        install(new TransactionalSimpleModule());
        bind(PersistenceManager.class).to(ThreadLocalPersistenceManager.class);
    }
}
