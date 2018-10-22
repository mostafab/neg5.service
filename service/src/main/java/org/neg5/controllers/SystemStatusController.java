package org.neg5.controllers;

import org.neg5.annotations.Controller;

import static spark.Spark.get;

/**
 * System status router
 */
@Controller("/neg5-api/system-status")
public class SystemStatusController extends AbstractController {

    @Override
    public void registerRoutes() {
        get("", (request, response) ->  "Status: OK");
    }
}
