package org.neg5.mappers;

import com.google.inject.Singleton;
import org.neg5.TournamentMatchPhaseDTO;
import org.neg5.data.TournamentMatchPhase;

@Singleton
public class TournamentMatchPhaseMapper extends AbstractObjectMapper<TournamentMatchPhase, TournamentMatchPhaseDTO> {

    public TournamentMatchPhaseMapper() {
        super(TournamentMatchPhase.class, TournamentMatchPhaseDTO.class);
    }
}
