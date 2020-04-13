package org.neg5.controllers;

import com.google.inject.Inject;
import org.eclipse.jetty.http.HttpStatus;
import org.neg5.annotations.Controller;
import org.neg5.login.DuplicateLoginException;
import org.neg5.managers.AccountCreationDTO;
import org.neg5.managers.AccountManager;
import org.neg5.util.RequestHelper;

@Controller("/neg5-api/accounts")
public class AccountController extends AbstractJsonController {

    @Inject private AccountManager accountManager;
    @Inject private RequestHelper requestHelper;

    @Override
    public void registerRoutes() {
        post("", (req, res) -> {
            try {
                AccountCreationDTO account = requestHelper.readFromRequest(req, AccountCreationDTO.class);
                return accountManager.createAccount(account);
            } catch (DuplicateLoginException e) {
                res.status(HttpStatus.FORBIDDEN_403);
                return "A login with this username already exists";
            }
        });
    }
}
