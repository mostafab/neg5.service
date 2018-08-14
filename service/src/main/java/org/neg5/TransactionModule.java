package org.neg5;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.ServletModule;
import org.neg5.module.JpaConfigurationModule;

import javax.sql.DataSource;

public class TransactionModule extends ServletModule {

    @Override
    public void configureServlets() {
        install(new JpaConfigurationModule());
        install(new JpaPersistModule("persistence"));
    }
}
