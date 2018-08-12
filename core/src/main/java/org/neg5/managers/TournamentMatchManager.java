package org.neg5.managers;

import com.google.inject.Inject;
import org.neg5.TournamentMatchDTO;
import org.neg5.daos.TournamentMatchDAO;
import org.neg5.data.TournamentMatch;
import org.neg5.mappers.TournamentMatchMapper;

public class TournamentMatchManager extends AbstractManager<TournamentMatch, TournamentMatchDTO> {

    @Inject private TournamentMatchMapper tournamentMatchMapper;
    @Inject private TournamentMatchDAO tournamentMatchDAO;

    @Override
    protected TournamentMatchMapper getMapper() {
        return tournamentMatchMapper;
    }

    @Override
    protected TournamentMatchDAO getDAO() {
        return tournamentMatchDAO;
    }
}
