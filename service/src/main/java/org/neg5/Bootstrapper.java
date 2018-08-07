package org.neg5;

import com.google.inject.Inject;

import org.neg5.filters.RequestFilter;
import org.neg5.routers.Router;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class Bootstrapper {

    @Inject private Set<Router> routers;
    @Inject private Set<RequestFilter> filters;

    private static final Logger LOGGER = LoggerFactory.getLogger(Bootstrapper.class);

    public void bootstrap() {
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
