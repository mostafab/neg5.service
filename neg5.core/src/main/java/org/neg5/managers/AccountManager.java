package org.neg5.managers;

import com.google.inject.Inject;
import org.neg5.daos.AccountDAO;

public class AccountManager {

    private final AccountDAO accountDAO;

    @Inject
    public AccountManager(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public boolean authenticateUser(String username, String password) {
        return false;
    }
}
