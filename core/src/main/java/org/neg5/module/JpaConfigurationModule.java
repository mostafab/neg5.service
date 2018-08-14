package org.neg5.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.zaxxer.hikari.HikariDataSource;
import org.neg5.core.TransactionalSimpleModule;

import javax.inject.Named;
import javax.sql.DataSource;

public class JpaConfigurationModule extends AbstractModule {

    protected void configure() {
        install(new TransactionalSimpleModule());
    }

//    public static final String DATA_SOURCE_PROP_NAME = "Neg5DataSource";
//
//    @Provides
//    @Named(DATA_SOURCE_PROP_NAME)
//    public DataSource provideDataSource() {
//        DataSource ds = new HikariDataSource();
//        return ds;
//    }
}
