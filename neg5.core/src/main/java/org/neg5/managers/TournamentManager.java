package org.neg5.managers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.neg5.TournamentDTO;
import org.neg5.core.ReadOnly;
import org.neg5.core.ReadWrite;
import org.neg5.daos.TournamentDAO;
import org.neg5.data.Tournament;

import org.neg5.mappers.TournamentMapper;

@Singleton
public class TournamentManager extends AbstractDTOManager<Tournament, TournamentDTO, String> {

    @Inject
    @ReadWrite
    private TournamentDAO rwTournamentDAO;

    @Inject
    @ReadOnly
    private TournamentDAO roTournamentDAO;

    @Inject
    private TournamentMapper tournamentMapper;

    @Override
    protected TournamentDAO getRwDAO() {
        return rwTournamentDAO;
    }

    @Override
    public TournamentDAO getRoDAO() {
        return roTournamentDAO;
    }

    @Override
    protected TournamentMapper getMapper() {
        return tournamentMapper;
    }

    @Override
    protected String getIdFromDTO(TournamentDTO tournamentDTO) {
        return tournamentDTO.getId();
    }
}
