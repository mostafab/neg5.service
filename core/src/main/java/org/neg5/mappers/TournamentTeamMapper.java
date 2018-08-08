package org.neg5.mappers;

import org.neg5.TournamentTeamDTO;
import org.neg5.data.TournamentTeam;

public class TournamentTeamMapper extends AbstractObjectMapper<TournamentTeam, TournamentTeamDTO> {

    protected TournamentTeamMapper() {
        super(TournamentTeam.class, TournamentTeamDTO.class);
    }

    @Override
    protected void addMappings() {
        getTypeMap().addMappings(mapper -> {
           mapper.map(team -> team.getTournament().getId(), TournamentTeamDTO::setTournamentId);
        });
    }
}
