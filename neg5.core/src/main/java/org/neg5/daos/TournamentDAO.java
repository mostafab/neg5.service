package org.neg5.daos;

import org.neg5.data.Tournament;

public class TournamentDAO extends AbstractDAO<Tournament, String> {

    public TournamentDAO() {
        super(Tournament.class);
    }
}