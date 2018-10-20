package org.neg5.daos;

import com.google.inject.Singleton;
import org.neg5.data.TournamentTeam;

@Singleton
public class TournamentTeamDAO extends AbstractDAO<TournamentTeam, String> {

    protected TournamentTeamDAO() {
        super(TournamentTeam.class);
    }
}
