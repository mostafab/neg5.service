package org.neg5.daos;

import org.neg5.data.TournamentMatchPhase;
import org.neg5.data.embeddables.MatchPhaseId;

public class TournamentMatchPhaseDAO extends AbstractDAO<TournamentMatchPhase, MatchPhaseId> {

    TournamentMatchPhaseDAO() {
        super(TournamentMatchPhase.class);
    }
}
