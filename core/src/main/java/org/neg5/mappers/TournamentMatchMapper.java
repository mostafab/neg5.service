package org.neg5.mappers;

import org.neg5.TournamentMatchDTO;
import org.neg5.data.TournamentMatch;

public class TournamentMatchMapper extends AbstractObjectMapper<TournamentMatch, TournamentMatchDTO> {

    protected TournamentMatchMapper() {
        super(TournamentMatch.class, TournamentMatchDTO.class);
    }

    @Override
    protected void addMappings() {
        getTypeMap().addMappings(mapper -> {
            mapper.map(match -> match.getTournament().getId(), TournamentMatchDTO::setTournamentId);
            mapper.map(match -> match.getAddedBy().getUsername(), TournamentMatchDTO::setAddedBy);
        });
    }
}
