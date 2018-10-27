package org.neg5.managers;

import com.google.inject.Inject;
import org.neg5.TournamentTossupValueDTO;
import org.neg5.core.ReadOnly;
import org.neg5.core.ReadWrite;
import org.neg5.daos.TournamentTossupValueDAO;
import org.neg5.data.TournamentTossupValue;
import org.neg5.data.embeddables.TournamentTossupValueId;
import org.neg5.mappers.TournamentTossupValueMapper;

public class TournamentTossupValueManager extends
        AbstractDTOManager<TournamentTossupValue, TournamentTossupValueDTO, TournamentTossupValueId> {

    @Inject @ReadWrite private TournamentTossupValueDAO rwTournamentTossupValueDAO;
    @Inject @ReadOnly private TournamentTossupValueDAO roTournamentTossupValueDAO;

    @Inject private TournamentTossupValueMapper tournamentTossupValueMapper;

    @Override
    protected TournamentTossupValueDAO getRwDAO() {
        return rwTournamentTossupValueDAO;
    }

    @Override
    protected TournamentTossupValueDAO getRoDAO() {
        return roTournamentTossupValueDAO;
    }

    @Override
    protected TournamentTossupValueMapper getMapper() {
        return tournamentTossupValueMapper;
    }

    @Override
    protected TournamentTossupValueId getIdFromDTO(TournamentTossupValueDTO tournamentTossupValueDTO) {
        return getMapper().mergeToEntity(tournamentTossupValueDTO).getId();
    }
}
