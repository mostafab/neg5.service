package org.neg5.mappers;

import com.google.inject.Singleton;
import org.neg5.TournamentPhaseDTO;
import org.neg5.data.TournamentPhase;

@Singleton
public class TournamentPhaseMapper extends AbstractObjectMapper<TournamentPhase, TournamentPhaseDTO> {

    protected TournamentPhaseMapper() {
        super(TournamentPhase.class, TournamentPhaseDTO.class);
    }

    @Override
    protected void addMappings() {
        getTypeMap().addMappings(mapper -> {
            mapper.map(phase -> phase.getTournament().getId(), TournamentPhaseDTO::setTournamentId);
        });
    }
}
