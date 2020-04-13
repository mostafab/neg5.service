package org.neg5.filters;

import com.google.inject.Inject;
import org.neg5.auth.Neg5TokenCookieNameProvider;
import org.neg5.core.CurrentUserContext;
import org.neg5.core.CurrentUserContextUtil;

import static spark.Spark.afterAfter;
import static spark.Spark.before;

public class CurrentUserContextFilter implements RequestFilter {

    @Inject private CurrentUserContext currentUserContext;
    @Inject private CurrentUserContextUtil userContextUtil;
    @Inject private Neg5TokenCookieNameProvider cookieNameSupplier;

    @Override
    public void registerFilter() {
        before((request, response) -> {
            String token = request.cookie(cookieNameSupplier.get());
            if (token != null) {
                currentUserContext.set(userContextUtil.getUserData(token));
            }
        });

        afterAfter((req, res) -> currentUserContext.clear());
    }
}
