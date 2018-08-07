package org.neg5.routers;

import static spark.Spark.get;

/**
 * System status router
 */
public class SystemStatusRouter implements Router {

    @Override
    public void registerRoutes() {
        get("/system-status", (request, response) ->  "Status: OK");
    }
}
