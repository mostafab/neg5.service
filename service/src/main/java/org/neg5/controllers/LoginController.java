package org.neg5.controllers;

import com.google.inject.Inject;
import org.eclipse.jetty.http.HttpStatus;
import org.neg5.auth.LoginAuthenticator;
import spark.Spark;

import static spark.Spark.path;

public class LoginController extends AbstractController {

    @Inject private LoginAuthenticator loginAuthenticator;

    @Override
    public void registerRoutes() {
        path("/neg5-api/login", () -> {
           Spark.post("", (req, res) -> {
               if (loginAuthenticator.loginByRequest(req, res)) {
                   return "OK";
               }
               res.status(HttpStatus.FORBIDDEN_403);
               return "Invalid";
           });
        });
    }
}
