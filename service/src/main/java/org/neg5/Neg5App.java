package org.neg5;

import com.google.inject.Inject;
import org.neg5.filters.RequestFilter;
import org.neg5.routers.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.servlet.SparkApplication;

import java.util.Set;

import static spark.Spark.port;

public class Neg5App implements SparkApplication {

    @Inject private Set<Router> routers;
    @Inject private Set<RequestFilter> filters;

    private static final Logger LOGGER = LoggerFactory.getLogger(Neg5App.class);

    @Override
    public synchronized void init() {
        bootstrap();
    }

    private void bootstrap() {
        port(1337);
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
}
