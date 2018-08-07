package org.neg5.routers;

import com.google.inject.Inject;
import org.neg5.transformers.JsonTransformer;
import org.neg5.transformers.JsonTransformerProvider;
import spark.Route;
import spark.Spark;

/**
 * Controller that handles converting responses to JSON payloads
 */
public abstract class AbstractJsonRouter implements Router {

    @Inject
    private JsonTransformerProvider jsonTransformerProvider;

    protected void get(String path, Route route) {
        Spark.get(path, route, getJsonTransformer());
    }

    protected JsonTransformer getJsonTransformer() {
        return jsonTransformerProvider.get();
    }

}
