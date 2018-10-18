package org.neg5.managers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.neg5.TournamentDTO;
import org.neg5.daos.TournamentDAO;
import org.neg5.data.Tournament;

import org.neg5.mappers.TournamentMapper;

@Singleton
public class TournamentManager extends AbstractManager<Tournament, TournamentDTO> {

    @Inject
    private TournamentDAO tournamentDAO;

    @Inject
    private TournamentMapper tournamentMapper;

    @Override
    protected TournamentDAO getDAO() {
        return tournamentDAO;
    }

    @Override
    protected TournamentMapper getMapper() {
        return tournamentMapper;
    }
}
