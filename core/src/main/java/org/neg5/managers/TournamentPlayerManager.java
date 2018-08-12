package org.neg5.managers;

import com.google.inject.Inject;
import org.neg5.TournamentPlayerDTO;

import org.neg5.daos.TournamentPlayerDAO;
import org.neg5.data.TournamentPlayer;
import org.neg5.mappers.TournamentPlayerMapper;

public class TournamentPlayerManager extends AbstractManager<TournamentPlayer, TournamentPlayerDTO> {

    @Inject private TournamentPlayerMapper tournamentPlayerMapper;
    @Inject private TournamentPlayerDAO tournamentPlayerDAO;

    @Override
    protected TournamentPlayerMapper getMapper() {
        return tournamentPlayerMapper;
    }

    @Override
    protected TournamentPlayerDAO getDAO() {
        return tournamentPlayerDAO;
    }
}