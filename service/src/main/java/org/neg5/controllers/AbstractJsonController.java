package org.neg5.controllers;

import com.google.gson.Gson;
import com.google.inject.Inject;
import org.neg5.core.GsonProvider;
import org.neg5.transformers.JsonTransformer;
import org.neg5.transformers.JsonTransformerProvider;

import spark.Route;

import java.util.function.Function;

/**
 * Controller that handles converting responses to JSON payloads
 */
public abstract class AbstractJsonController extends AbstractController {

    @Inject
    private JsonTransformerProvider jsonTransformerProvider;

    @Inject private GsonProvider gsonProvider;

    @Override
    protected JsonTransformer getResponseTransformer() {
        return jsonTransformerProvider.get();
    }

    protected Gson getGson() {
        return gsonProvider.get();
    }

    protected <T> void post(String path, Class<T> requestBodyClazz, Function<T, Route> requestHandler) {
        super.post(path, (req, res) -> {
            T requestBody = getGson().fromJson(req.body(), requestBodyClazz);
            return requestHandler.apply(requestBody).handle(req, res);
        });
    }

    @Override
    protected Route enrichRoute(Route handler) {
        return (request, response) -> {
            response.type("application/json");
            return handler.handle(request, response);
        };
    }

}
