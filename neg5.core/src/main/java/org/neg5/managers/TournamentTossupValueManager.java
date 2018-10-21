package org.neg5.managers;

import com.google.inject.Inject;
import org.neg5.TournamentTossupValueDTO;
import org.neg5.daos.TournamentTossupValueDAO;
import org.neg5.data.TournamentTossupValue;
import org.neg5.data.embeddables.TournamentTossupValueId;
import org.neg5.mappers.TournamentTossupValueMapper;

public class TournamentTossupValueManager extends
        AbstractManager<TournamentTossupValue, TournamentTossupValueDTO, TournamentTossupValueId> {

    @Inject private TournamentTossupValueDAO tournamentTossupValueDAO;
    @Inject private TournamentTossupValueMapper tournamentTossupValueMapper;

    @Override
    protected TournamentTossupValueDAO getDAO() {
        return tournamentTossupValueDAO;
    }

    @Override
    protected TournamentTossupValueMapper getMapper() {
        return tournamentTossupValueMapper;
    }
}