package org.neg5;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {

    public static void main(String args[]) {
        Injector injector = Guice.createInjector(new Neg5WebModule());
        Neg5App app = injector.getInstance(Neg5App.class);
        app.init();
    }
}
