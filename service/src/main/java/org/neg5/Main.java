package org.neg5;

import com.google.inject.Guice;
import com.google.inject.Injector;
import neg5.db.flyway.module.FlywayModule;
import org.neg5.jwt.module.JwtSigningModule;
import org.neg5.module.DataAccessModule;
import org.neg5.module.StatsCacheModule;
import org.neg5.module.SystemPropertiesModule;

public class Main {

    public static void main(String args[]) throws Exception {
        Injector injector = Guice.createInjector(
                new SystemPropertiesModule(),
                new DataAccessModule(),
                new FlywayModule(),
                new ControllersModule(),
                new FilterModule(),
                new JwtSigningModule("NEG5_JWT"),
                new StatsCacheModule()
        );
        Neg5App app = injector.getInstance(Neg5App.class);
        app.init();
    }
}
