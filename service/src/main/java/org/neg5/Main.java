package org.neg5;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {

    public static void main(String args[]) {
        Injector injector = Guice.createInjector(
                new RouterModule(),
                new FilterModule()
        );

        Bootstrapper bootstrapper = injector.getInstance(Bootstrapper.class);
        bootstrapper.bootstrap();
    }
}
