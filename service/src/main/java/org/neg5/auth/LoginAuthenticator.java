package org.neg5.auth;

import com.google.inject.Inject;
import org.neg5.core.GsonProvider;
import org.neg5.managers.AccountManager;
import spark.Request;
import spark.Response;

public class LoginAuthenticator {

    private final AccountManager accountManager;
    private final GsonProvider gsonProvider;

    @Inject
    public LoginAuthenticator(AccountManager accountManager,
                              GsonProvider gsonProvider) {
        this.accountManager = accountManager;
        this.gsonProvider = gsonProvider;
    }

    private static final String NF_TOKEN_COOKIE_NAME = "nfToken";

    public boolean loginByRequest(Request request, Response response) {
        LoginCreds credentials = gsonProvider.get().fromJson(request.body(), LoginCreds.class);
        if (accountManager.authenticateUser(credentials.username, credentials.password)) {
            response.cookie(NF_TOKEN_COOKIE_NAME, "TEST");
            return true;
        }
        return false;
    }

    static final class LoginCreds {
        private String username;
        private String password;

        String getUsername() {
            return username;
        }

        String getPassword() {
            return password;
        }
    }
}
