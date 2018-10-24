package org.neg5.managers;

import com.google.inject.Inject;
import org.neg5.TournamentTossupValueDTO;
import org.neg5.core.ReadOnly;
import org.neg5.daos.TournamentTossupValueDAO;
import org.neg5.data.TournamentTossupValue;
import org.neg5.data.embeddables.TournamentTossupValueId;
import org.neg5.mappers.TournamentTossupValueMapper;

public class TournamentTossupValueManager extends
        AbstractDTOManager<TournamentTossupValue, TournamentTossupValueDTO, TournamentTossupValueId> {

    @Inject @ReadOnly private TournamentTossupValueDAO tournamentTossupValueDAO;
    @Inject private TournamentTossupValueMapper tournamentTossupValueMapper;

    @Override
    protected TournamentTossupValueDAO getDAO() {
        return tournamentTossupValueDAO;
    }

    @Override
    protected TournamentTossupValueMapper getMapper() {
        return tournamentTossupValueMapper;
    }

    @Override
    protected TournamentTossupValueId getIdFromDTO(TournamentTossupValueDTO tournamentTossupValueDTO) {
        TournamentTossupValueId id = new TournamentTossupValueId();
        id.setValue(tournamentTossupValueDTO.getValue());
        id.setTournamentId(tournamentTossupValueDTO.getTournamentId());
        return id;
    }
}
