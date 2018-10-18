package org.neg5.managers;

import com.google.inject.Inject;
import org.neg5.TournamentPhaseDTO;

import org.neg5.daos.TournamentPhaseDAO;
import org.neg5.data.TournamentPhase;
import org.neg5.mappers.TournamentPhaseMapper;

public class TournamentPhaseManager extends AbstractManager<TournamentPhase, TournamentPhaseDTO> {

    @Inject private TournamentPhaseDAO tournamentPhaseDAO;
    @Inject private TournamentPhaseMapper tournamentPhaseMapper;

    @Override
    protected TournamentPhaseDAO getDAO() {
        return tournamentPhaseDAO;
    }

    @Override
    protected TournamentPhaseMapper getMapper() {
        return tournamentPhaseMapper;
    }
}
