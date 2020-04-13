package org.neg5.managers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.neg5.TournamentTossupValueDTO;
import org.neg5.daos.TournamentTossupValueDAO;
import org.neg5.data.TournamentTossupValue;
import org.neg5.data.embeddables.TournamentTossupValueId;
import org.neg5.mappers.TournamentTossupValueMapper;

@Singleton
public class TournamentTossupValueManager extends
        AbstractDTOManager<TournamentTossupValue, TournamentTossupValueDTO, TournamentTossupValueId> {

    @Inject private TournamentTossupValueDAO rwTournamentTossupValueDAO;

    @Inject private TournamentTossupValueMapper tournamentTossupValueMapper;

    @Override
    protected TournamentTossupValueDAO getRwDAO() {
        return rwTournamentTossupValueDAO;
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
