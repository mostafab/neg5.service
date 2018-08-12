package org.neg5;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class SparkGuiceServletContextListener extends GuiceServletContextListener {

    private Injector injector;

    @Override
    protected Injector getInjector() {
        if (injector == null) {
            injector = Guice.createInjector(new TransactionModule(), new RouterModule(), new FilterModule());
        }
        return injector;
    }
}
