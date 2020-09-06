package org.neg5.daos;

import org.neg5.data.TournamentTeamPool;
import org.neg5.data.embeddables.TournamentTeamPoolId;

public class TournamentTeamPoolDAO
        extends AbstractDAO<TournamentTeamPool, TournamentTeamPoolId> {

    protected TournamentTeamPoolDAO() {
        super(TournamentTeamPool.class);
    }
}
