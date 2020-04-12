package org.neg5.managers;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import org.mindrot.jbcrypt.BCrypt;
import org.neg5.daos.AccountDAO;
import org.neg5.data.Account;

public class AccountManager {

    private final AccountDAO accountDAO;

    @Inject
    public AccountManager(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public boolean verifyPassword(String username, String password) {
        String hashedPassword = getHashedPassword(username);
        return BCrypt.checkpw(password, hashedPassword);
    }

    @Transactional
    String getHashedPassword(String username) {
        Account account = accountDAO.getByUsername(username);
        if (account == null) {
            return null;
        }
        return account.getHashedPassword();
    }
}
