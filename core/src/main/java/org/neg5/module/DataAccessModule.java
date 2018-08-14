package org.neg5.module;

import com.google.inject.AbstractModule;
import org.neg5.core.TransactionalSimpleModule;

public class DataAccessModule extends AbstractModule {

    protected void configure() {
        install(new TransactionalSimpleModule());
    }
}
