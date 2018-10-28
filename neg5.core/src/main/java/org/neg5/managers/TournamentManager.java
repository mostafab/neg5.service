package org.neg5.managers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.neg5.TournamentDTO;
import org.neg5.daos.TournamentDAO;
import org.neg5.data.Tournament;

import org.neg5.mappers.TournamentMapper;

@Singleton
public class TournamentManager extends AbstractDTOManager<Tournament, TournamentDTO, String> {

    @Inject
    private TournamentDAO rwTournamentDAO;

    @Inject
    private TournamentMapper tournamentMapper;

    @Override
    protected TournamentDAO getRwDAO() {
        return rwTournamentDAO;
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
