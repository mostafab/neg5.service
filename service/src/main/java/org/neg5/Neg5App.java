package org.neg5;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.google.inject.persist.PersistService;
import org.neg5.filters.RequestFilter;
import org.neg5.module.ReadOnlyModule;
import org.neg5.module.ReadWriteModule;
import org.neg5.module.SystemProperties;
import org.neg5.controllers.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.servlet.SparkApplication;

import java.util.Set;

import static spark.Spark.port;

public class Neg5App implements SparkApplication {

    @Inject private Set<BaseController> controllers;
    @Inject private Set<RequestFilter> filters;
    @Inject private SystemProperties systemProperties;

    private final PersistService roPersistService;
    private final PersistService rwPersistService;

    private static final int DEFAULT_PORT = 1337;
    private static final String PORT_PROP_NAME = "PORT";

    private static final Logger LOGGER = LoggerFactory.getLogger(Neg5App.class);

    @Inject
    public Neg5App(
            @Named(ReadOnlyModule.READ_ONLY_PERSIST_SERVICE_PROP) PersistService roPersistService,
            @Named(ReadWriteModule.READ_WRITE_PERSIST_SERVICE_PROP) PersistService rwPersistService) {
        this.roPersistService = roPersistService;
        this.rwPersistService = rwPersistService;
    }

    @Override
    public synchronized void init() {
        roPersistService.start();
        rwPersistService.start();

        port(getPort());
        initRoutes();
        initFilters();
    }

    private void initRoutes() {
        controllers.forEach(BaseController::registerRoutes);
    }

    private void initFilters() {
        filters.forEach(filter -> {
            filter.registerFilter();
            LOGGER.info("Registered filters in {}", filter.getClass());
        });
    }

    private Integer getPort() {
        if (systemProperties.getInt(PORT_PROP_NAME) != null) {
            return systemProperties.getInt(PORT_PROP_NAME);
        }
        return DEFAULT_PORT;
    }
}
