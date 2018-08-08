package org.neg5.managers;

import com.google.inject.Inject;
import org.neg5.TournamentDTO;
import org.neg5.daos.TournamentDAO;
import org.neg5.data.Tournament;

public class TournamentManager extends AbstractManager<Tournament, TournamentDTO> {

    @Inject
    private TournamentDAO tournamentDAO;

    @Override
    protected TournamentDAO getDAO() {
        return tournamentDAO;
    }
}
