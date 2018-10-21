package org.neg5.controllers;

/**
 * An interface whose implementing classes should map request uris to handlers
 */
public interface Controller {

    /**
     * Register routes.
     */
    void registerRoutes();
}
