package org.neg5.mappers;

import org.neg5.TournamentMatchPhaseDTO;
import org.neg5.data.TournamentMatchPhase;

public class TournamentMatchPhaseMapper extends AbstractObjectMapper<TournamentMatchPhase, TournamentMatchPhaseDTO> {

    public TournamentMatchPhaseMapper() {
        super(TournamentMatchPhase.class, TournamentMatchPhaseDTO.class);
    }
}
