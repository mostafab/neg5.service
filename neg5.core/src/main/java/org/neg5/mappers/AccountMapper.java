package org.neg5.mappers;

import com.google.inject.Singleton;
import org.neg5.AccountDTO;
import org.neg5.data.Account;

@Singleton
public class AccountMapper extends AbstractObjectMapper<Account, AccountDTO> {

    protected AccountMapper() {
        super(Account.class, AccountDTO.class);
    }
}
