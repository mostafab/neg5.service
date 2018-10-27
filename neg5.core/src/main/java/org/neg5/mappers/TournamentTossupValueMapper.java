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
    public TournamentTossupValue mergeToEntity(TournamentTossupValueDTO tournamentTossupValueDTO) {
        TournamentTossupValue entity = super.mergeToEntity(tournamentTossupValueDTO);
        entity.getId().setValue(tournamentTossupValueDTO.getValue());
        return entity;
    }

    @Override
    protected void addMappings() {
        getEntityToDTOTypeMap().addMappings(mapper -> {
            mapper.map(
                    tournamentTossupValue -> tournamentTossupValue.getId().getValue(),
                    TournamentTossupValueDTO::setValue
            );
        });
    }
}
