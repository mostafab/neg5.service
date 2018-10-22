package org.neg5.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.ResponseTransformer;
import spark.Route;
import spark.Spark;

public abstract class AbstractController implements Controller {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    protected void get(String path, Route route) {
        String fullPath = constructPath(path);
        if (getResponseTransformer() == null) {
            Spark.get(fullPath, enrichRoute(route));
        } else {
            Spark.get(fullPath, enrichRoute(route), getResponseTransformer());
        }
        LOGGER.info("Mapped GET route {}", path);
    }

    protected Route enrichRoute(Route route) {
        return route;
    }

    protected ResponseTransformer getResponseTransformer() {
        return null;
    }

    private String constructPath(String path) {
        org.neg5.annotations.Controller annotation = this.getClass()
                .getAnnotation(org.neg5.annotations.Controller.class);

        if (annotation == null) {
            return path;
        }
        return annotation.value() + path;
    }
}
