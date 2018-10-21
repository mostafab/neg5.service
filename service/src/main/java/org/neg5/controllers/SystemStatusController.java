package org.neg5.controllers;

import static spark.Spark.get;

/**
 * System status router
 */
public class SystemStatusController implements Controller {

    @Override
    public void registerRoutes() {
        get("neg5-api/system-status", (request, response) ->  "Status: OK");
    }
}
