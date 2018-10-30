package org.neg5.filters;

import com.google.inject.Inject;
import org.neg5.core.CurrentUserContext;

import static spark.Spark.afterAfter;
import static spark.Spark.before;

public class CurrentUserContextFilter implements RequestFilter {

    @Inject private CurrentUserContext currentUserContext;

    private static final String TOKEN_HEADER_NAME = "X-Neg5-Token";

    @Override
    public void registerFilter() {
        before((request, response) -> {
            String token = request.headers(TOKEN_HEADER_NAME);
            currentUserContext.set(token);
        });

        afterAfter((req, res) -> currentUserContext.clear());
    }
}
