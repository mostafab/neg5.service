package org.neg5.controllers;

import com.google.inject.Inject;
import org.neg5.transformers.JsonTransformer;
import org.neg5.transformers.JsonTransformerProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Route;
import spark.Spark;

/**
 * Controller that handles converting responses to JSON payloads
 */
public abstract class AbstractJsonController implements Controller {

    @Inject
    private JsonTransformerProvider jsonTransformerProvider;

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    protected void get(String path, Route route) {
        Spark.get(path, enrichRoute(route), getJsonTransformer());
        LOGGER.info("Mapped GET route {}", path);
    }

    protected JsonTransformer getJsonTransformer() {
        return jsonTransformerProvider.get();
    }

    private Route enrichRoute(Route handler) {
        return (request, response) -> {
            response.type("application/json");
            return handler.handle(request, response);
        };
    }

}