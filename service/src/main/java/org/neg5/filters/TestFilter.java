package org.neg5.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;

import static spark.Spark.before;

public class TestFilter implements RequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestFilter.class);

    @Override
    public void registerFilter() {
        before((request, response) -> {
            LOGGER.info("This is middleware logging the request at {}", Instant.now());
        });
    }
}
