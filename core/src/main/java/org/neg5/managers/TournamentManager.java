package org.neg5.managers;

import com.google.inject.Inject;
import org.neg5.TournamentDTO;
import org.neg5.daos.TournamentDAO;

public class TournamentManager {

    @Inject
    private TournamentDAO tournamentDAO;

    public TournamentDTO get(String id) {
        tournamentDAO.get(id);
        TournamentDTO tournament = new TournamentDTO();
        tournament.setId(id);

        return tournament;
    }
}
