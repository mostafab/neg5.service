package org.neg5.controllers;

import com.google.inject.Inject;
import org.neg5.transformers.JsonTransformer;
import org.neg5.transformers.JsonTransformerProvider;

import spark.Route;

/**
 * Controller that handles converting responses to JSON payloads
 */
public abstract class AbstractJsonController extends AbstractController {

    @Inject
    private JsonTransformerProvider jsonTransformerProvider;

    @Override
    protected JsonTransformer getResponseTransformer() {
        return jsonTransformerProvider.get();
    }

    @Override
    protected Route enrichRoute(Route handler) {
        return (request, response) -> {
            response.type("application/json");
            return handler.handle(request, response);
        };
    }

}
