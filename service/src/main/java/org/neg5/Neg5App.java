package org.neg5;

import com.google.inject.Inject;
import com.google.inject.persist.PersistService;
import org.neg5.filters.RequestFilter;
import org.neg5.module.JpaConfigurationModule;
import org.neg5.routers.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.servlet.SparkApplication;

import javax.inject.Named;
import javax.sql.DataSource;
import java.util.Set;

public class Neg5App implements SparkApplication {

    @Inject private Set<Router> routers;
    @Inject private Set<RequestFilter> filters;
    @Inject private PersistService persistService;

    private static final Logger LOGGER = LoggerFactory.getLogger(Neg5App.class);

    @Override
    public synchronized void init() {
        bootstrap();
    }

    private void bootstrap() {
        initRoutes();
        initFilters();
        persistService.start();
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
