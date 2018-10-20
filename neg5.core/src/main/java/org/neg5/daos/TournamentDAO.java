package org.neg5.daos;

import com.google.inject.Singleton;
import org.neg5.data.Tournament;

@Singleton
public class TournamentDAO extends AbstractDAO<Tournament, String> {

    public TournamentDAO() {
        super(Tournament.class);
    }
}