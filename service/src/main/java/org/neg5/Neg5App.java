package org.neg5;

import com.google.inject.Inject;
import org.neg5.filters.RequestFilter;
import org.neg5.module.SystemProperties;
import org.neg5.routers.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.servlet.SparkApplication;

import java.util.Set;

import static spark.Spark.port;

public class Neg5App implements SparkApplication {

    @Inject private Set<Router> routers;
    @Inject private Set<RequestFilter> filters;

    @Inject private SystemProperties systemProperties;

    private static final int DEFAULT_PORT = 1337;
    private static final String PORT_PROP_NAME = "PORT";

    private static final Logger LOGGER = LoggerFactory.getLogger(Neg5App.class);

    @Override
    public synchronized void init() {
        port(getPort());
        initRoutes();
        initFilters();
    }

    private void initRoutes() {
        routers.forEach(router -> {
            router.registerRoutes();
            LOGGER.info("Registered routes in {}", router.getClass());
        });
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
