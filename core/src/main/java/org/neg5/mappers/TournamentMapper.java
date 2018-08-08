package org.neg5.mappers;

import org.neg5.TournamentDTO;
import org.neg5.data.Tournament;

public class TournamentMapper extends AbstractObjectMapper<Tournament, TournamentDTO> {

    protected TournamentMapper() {
        super(Tournament.class, TournamentDTO.class);
    }

    @Override
    protected void addMappings() {
        getTypeMap().addMappings(mapper -> {
           mapper.map(src -> src.getDirector().getUsername(), TournamentDTO::setDirectorId);
        });
    }
}
