package org.neg5.daos;

import org.neg5.data.TournamentPlayer;

public class TournamentPlayerDAO extends AbstractDAO<TournamentPlayer, String> {

    protected TournamentPlayerDAO() {
        super(TournamentPlayer.class);
    }
}
