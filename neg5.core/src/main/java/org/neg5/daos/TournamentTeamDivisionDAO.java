package org.neg5.daos;

import org.neg5.data.TournamentTeamDivision;
import org.neg5.data.embeddables.TournamentTeamDivisionId;

public class TournamentTeamDivisionDAO
        extends AbstractDAO<TournamentTeamDivision, TournamentTeamDivisionId> {

    protected TournamentTeamDivisionDAO() {
        super(TournamentTeamDivision.class);
    }
}
