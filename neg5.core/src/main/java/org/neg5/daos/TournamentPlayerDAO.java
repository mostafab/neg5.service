package org.neg5.daos;

import com.google.inject.Singleton;
import org.neg5.data.TournamentPlayer;

@Singleton
public class TournamentPlayerDAO extends AbstractDAO<TournamentPlayer> {

    protected TournamentPlayerDAO() {
        super(TournamentPlayer.class);
    }
}
