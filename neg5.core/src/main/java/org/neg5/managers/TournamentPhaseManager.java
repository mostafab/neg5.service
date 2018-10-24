package org.neg5.managers;

import com.google.inject.Inject;
import org.neg5.TournamentPhaseDTO;

import org.neg5.core.ReadOnly;
import org.neg5.daos.TournamentPhaseDAO;
import org.neg5.data.TournamentPhase;
import org.neg5.mappers.TournamentPhaseMapper;

public class TournamentPhaseManager extends AbstractDTOManager<TournamentPhase, TournamentPhaseDTO, String> {

    @Inject @ReadOnly private TournamentPhaseDAO tournamentPhaseDAO;
    @Inject private TournamentPhaseMapper tournamentPhaseMapper;

    @Override
    protected TournamentPhaseDAO getDAO() {
        return tournamentPhaseDAO;
    }

    @Override
    protected TournamentPhaseMapper getMapper() {
        return tournamentPhaseMapper;
    }

    @Override
    protected String getIdFromDTO(TournamentPhaseDTO tournamentPhaseDTO) {
        return tournamentPhaseDTO.getId();
    }
}
