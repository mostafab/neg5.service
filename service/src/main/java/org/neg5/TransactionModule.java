package org.neg5;

import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.ServletModule;
import org.neg5.module.DataAccessModule;

public class TransactionModule extends ServletModule {

    @Override
    public void configureServlets() {
        install(new DataAccessModule());
        install(new JpaPersistModule("persistence"));
    }
}
