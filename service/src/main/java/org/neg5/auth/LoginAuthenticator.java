package org.neg5.auth;

import com.google.inject.Inject;
import org.neg5.core.GsonProvider;
import org.neg5.jwt.JwtData;
import org.neg5.jwt.JwtManager;
import org.neg5.managers.AccountManager;
import spark.Request;
import spark.Response;

import java.time.Instant;

public class LoginAuthenticator {

    private final AccountManager accountManager;
    private final GsonProvider gsonProvider;
    private final JwtManager jwtManager;
    private final Neg5TokenCookieNameProvider cookieNameProvider;

    @Inject
    public LoginAuthenticator(AccountManager accountManager,
                              GsonProvider gsonProvider,
                              JwtManager jwtManager,
                              Neg5TokenCookieNameProvider cookieNameProvider) {
        this.accountManager = accountManager;
        this.gsonProvider = gsonProvider;
        this.jwtManager = jwtManager;
        this.cookieNameProvider = cookieNameProvider;
    }

    public boolean loginByRequest(Request request, Response response) {
        LoginCreds credentials = gsonProvider.get().fromJson(request.body(), LoginCreds.class);
        return loginByCredentials(credentials, response);
    }

    public boolean loginByCredentials(LoginCreds credentials, Response response) {
        if (accountManager.verifyPassword(credentials.getUsername(), credentials.getPassword())) {
            response.cookie(cookieNameProvider.get(), jwtManager.buildJwt(buildData(credentials)));
            return true;
        }
        return false;
    }

    private JwtData buildData(LoginCreds loginCreds) {
        return JwtData.newData()
            .put("username", loginCreds.getUsername())
            .put("issuedAt", Instant.now().toEpochMilli());
    }
}
