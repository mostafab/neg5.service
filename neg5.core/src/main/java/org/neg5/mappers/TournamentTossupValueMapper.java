package org.neg5.mappers;

import com.google.inject.Singleton;
import org.neg5.TournamentTossupValueDTO;
import org.neg5.data.TournamentTossupValue;

@Singleton
public class TournamentTossupValueMapper extends AbstractObjectMapper<TournamentTossupValue, TournamentTossupValueDTO> {

    protected TournamentTossupValueMapper() {
        super(TournamentTossupValue.class, TournamentTossupValueDTO.class);
    }

    @Override
    protected void addMappings() {
        getTypeMap().addMappings(mapper -> {
            mapper.map(
                    tournamentTossupValue -> tournamentTossupValue.getTournamentTossupValueId().getTournament().getId(),
                    TournamentTossupValueDTO::setTournamentId
            );
            mapper.map(
                    tournamentTossupValue -> tournamentTossupValue.getTournamentTossupValueId().getValue(),
                    TournamentTossupValueDTO::setValue
            );
        });
    }
}
