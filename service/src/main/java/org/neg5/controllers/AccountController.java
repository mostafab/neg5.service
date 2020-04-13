package org.neg5.controllers;

import com.google.inject.Inject;
import org.eclipse.jetty.http.HttpStatus;
import org.neg5.AccountDTO;
import org.neg5.annotations.Controller;
import org.neg5.auth.LoginAuthenticator;
import org.neg5.auth.LoginCreds;
import org.neg5.login.DuplicateLoginException;
import org.neg5.managers.AccountCreationDTO;
import org.neg5.managers.AccountManager;
import org.neg5.util.RequestHelper;
import spark.Request;
import spark.Response;

@Controller("/neg5-api/accounts")
public class AccountController extends AbstractJsonController {

    @Inject private AccountManager accountManager;
    @Inject private RequestHelper requestHelper;

    @Inject private LoginAuthenticator loginAuthenticator;

    @Override
    public void registerRoutes() {
        post("", this::createAccountAndLogin);
    }

    private Object createAccountAndLogin(Request request, Response response) {
        try {
            AccountCreationDTO account = requestHelper.readFromRequest(request, AccountCreationDTO.class);
            AccountDTO createdAccount = accountManager.createAccount(account);
            loginAuthenticator.loginByCredentials(buildLoginCreds(account), response);
            return createdAccount;
        } catch (DuplicateLoginException e) {
            response.status(HttpStatus.FORBIDDEN_403);
            return "A login with this username already exists";
        }
    }

    private LoginCreds buildLoginCreds(AccountCreationDTO accountCreationDTO) {
        LoginCreds loginCreds = new LoginCreds();
        loginCreds.setPassword(accountCreationDTO.getPassword());
        loginCreds.setUsername(accountCreationDTO.getUsername());

        return loginCreds;
    }
}
