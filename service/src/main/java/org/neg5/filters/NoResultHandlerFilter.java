package org.neg5.filters;

import org.eclipse.jetty.http.HttpStatus;

import javax.persistence.NoResultException;

import static spark.Spark.exception;

public class NoResultHandlerFilter implements RequestFilter {

    @Override
    public void registerFilter() {
        exception(NoResultException.class, (exception, request, response) -> {
           response.status(HttpStatus.NOT_FOUND_404);
           response.body(exception.getMessage());
        });
    }
}
