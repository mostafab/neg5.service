package org.neg5.managers;

import com.google.inject.Inject;
import org.neg5.TournamentTeamDTO;
import org.neg5.daos.TournamentTeamDAO;
import org.neg5.data.TournamentTeam;

import org.neg5.mappers.TournamentTeamMapper;

public class TournamentTeamManager extends AbstractManager<TournamentTeam, TournamentTeamDTO> {

    @Inject private TournamentTeamDAO tournamentTeamDAO;

    @Inject private TournamentTeamMapper tournamentTeamMapper;

    @Override
    protected TournamentTeamDAO getDAO() {
        return tournamentTeamDAO;
    }

    @Override
    protected TournamentTeamMapper getMapper() {
        return tournamentTeamMapper;
    }
}
