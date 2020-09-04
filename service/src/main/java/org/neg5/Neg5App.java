package org.neg5;

import com.google.inject.Inject;
import neg5.db.flyway.Neg5DatabaseMigrator;
import org.neg5.core.PersistInitializer;
import org.neg5.module.Configuration;
import org.neg5.util.FilterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.servlet.SparkApplication;

import static spark.Spark.port;

public class Neg5App implements SparkApplication {

    @Inject private ControllerRegistry controllerRegistry;
    @Inject private FilterRegistry filterRegistry;
    @Inject private Configuration configuration;
    @Inject private Neg5DatabaseMigrator databaseMigrator;

    @Inject private PersistInitializer persistInitializer;

    private static final int DEFAULT_PORT = 1337;
    private static final String PORT_PROP_NAME = "PORT";

    private static final Logger LOGGER = LoggerFactory.getLogger(Neg5App.class);

    @Override
    public synchronized void init() {
        persistInitializer.start();

        port(getPort());
        initRoutes();
        initFilters();

        // Migrate DB after server starts
        databaseMigrator.migrateDatabase();
    }

    private void initRoutes() {
        controllerRegistry.initControllers();
    }

    private void initFilters() {
        filterRegistry.initFilters();
    }

    private Integer getPort() {
        if (configuration.getInt(PORT_PROP_NAME) != null) {
            return configuration.getInt(PORT_PROP_NAME);
        }
        return DEFAULT_PORT;
    }
}
