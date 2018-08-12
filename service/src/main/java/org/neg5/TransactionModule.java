package org.neg5;

import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.ServletModule;

public class TransactionModule extends ServletModule {

    @Override
    public void configureServlets() {
        install(new JpaPersistModule("persistence"));
    }
}
