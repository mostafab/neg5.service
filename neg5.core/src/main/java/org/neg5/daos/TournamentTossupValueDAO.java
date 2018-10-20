package org.neg5.daos;

import com.google.inject.Singleton;
import org.neg5.data.TournamentTossupValue;
import org.neg5.data.embeddables.TournamentTossupValueId;

@Singleton
public class TournamentTossupValueDAO extends AbstractDAO<TournamentTossupValue, TournamentTossupValueId> {

    private static final String TOURNAMENT_ID_ATTRIBUTE_PATH = "id.tournament.id";

    protected TournamentTossupValueDAO() {
        super(TournamentTossupValue.class);
    }

    @Override
    protected String getTournamentIdAttributePath() {
        return TOURNAMENT_ID_ATTRIBUTE_PATH;
    }
}
