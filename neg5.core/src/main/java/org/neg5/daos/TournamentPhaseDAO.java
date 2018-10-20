package org.neg5.daos;

import com.google.inject.Singleton;
import org.neg5.data.TournamentPhase;

@Singleton
public class TournamentPhaseDAO extends AbstractDAO<TournamentPhase> {

    protected TournamentPhaseDAO() {
        super(TournamentPhase.class);
    }
}
