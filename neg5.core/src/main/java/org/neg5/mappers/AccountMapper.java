package org.neg5.mappers;

import org.neg5.AccountDTO;
import org.neg5.data.Account;

public class AccountMapper extends AbstractObjectMapper<Account, AccountDTO> {

    protected AccountMapper() {
        super(Account.class, AccountDTO.class);
    }
}
