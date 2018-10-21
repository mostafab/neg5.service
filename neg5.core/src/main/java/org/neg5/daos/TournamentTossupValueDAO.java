package org.neg5.daos;

import com.google.inject.Singleton;
import org.neg5.data.TournamentTossupValue;
import org.neg5.data.embeddables.TournamentTossupValueId;

@Singleton
public class TournamentTossupValueDAO extends AbstractDAO<TournamentTossupValue, TournamentTossupValueId> {

    protected TournamentTossupValueDAO() {
        super(TournamentTossupValue.class);
    }
}
