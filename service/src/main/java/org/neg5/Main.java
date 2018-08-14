package org.neg5;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.neg5.module.DataAccessModule;

public class Main {

    public static void main(String args[]) throws Exception {
        Injector injector = Guice.createInjector(
                new DataAccessModule(),
                new RouterModule(),
                new FilterModule()
        );
        Neg5App app = injector.getInstance(Neg5App.class);
        app.init();
    }
}
