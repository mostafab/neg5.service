package org.neg5.managers;

import org.neg5.TournamentDTO;

public class TournamentManager {

    public TournamentDTO get(String id) {
        TournamentDTO tournament = new TournamentDTO();
        tournament.setId(id);

        return tournament;
    }
}
