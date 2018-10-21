package org.neg5;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.neg5.module.DataAccessModule;
import org.neg5.module.SystemPropertiesModule;

public class Main {

    public static void main(String args[]) throws Exception {
        Injector injector = Guice.createInjector(
                new SystemPropertiesModule(),
                new DataAccessModule(),
                new ControllersModule(),
                new FilterModule()
        );
        Neg5App app = injector.getInstance(Neg5App.class);
        app.init();
    }
}
