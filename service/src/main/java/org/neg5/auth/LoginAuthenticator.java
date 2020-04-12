package org.neg5.auth;

import com.google.inject.Inject;
import org.neg5.core.GsonProvider;
import org.neg5.jwt.JwtData;
import org.neg5.jwt.JwtManager;
import org.neg5.managers.AccountManager;
import spark.Request;
import spark.Response;

public class LoginAuthenticator {

    private final AccountManager accountManager;
    private final GsonProvider gsonProvider;
    private final JwtManager jwtManager;

    private static final String NF_TOKEN_COOKIE_NAME = "nfToken";

    @Inject
    public LoginAuthenticator(AccountManager accountManager,
                              GsonProvider gsonProvider,
                              JwtManager jwtManager) {
        this.accountManager = accountManager;
        this.gsonProvider = gsonProvider;
        this.jwtManager = jwtManager;
    }

    public boolean loginByRequest(Request request, Response response) {
        LoginCreds credentials = gsonProvider.get().fromJson(request.body(), LoginCreds.class);
        if (accountManager.verifyPassword(credentials.getUsername(), credentials.getPassword())) {
            response.cookie(NF_TOKEN_COOKIE_NAME, jwtManager.buildJwt(buildData(credentials)));
            return true;
        }
        return false;
    }

    private JwtData buildData(LoginCreds loginCreds) {
        return JwtData.newData()
            .put("username", loginCreds.getUsername());
    }
}
