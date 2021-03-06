package org.neg5.daos;

import org.neg5.data.TournamentTossupValue;
import org.neg5.data.embeddables.TournamentTossupValueId;

public class TournamentTossupValueDAO extends AbstractDAO<TournamentTossupValue, TournamentTossupValueId> {

    protected TournamentTossupValueDAO() {
        super(TournamentTossupValue.class);
    }

    @Override
    protected String getTournamentIdAttributePath() {
        return "id.tournament.id";
    }
}
