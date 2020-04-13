package org.neg5;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.neg5.controllers.BaseController;

import java.util.Set;

@Singleton
public class ControllerRegistry {

    @Inject
    private Set<BaseController> controllers;

    public void initControllers() {
        controllers.forEach(c -> c.registerRoutes());
    }
}
