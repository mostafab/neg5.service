package org.neg5.routers;

/**
 * An interface whose implementing classes should map request uris to handlers
 */
public interface Router {

    /**
     * Register routes.
     */
    void registerRoutes();
}
