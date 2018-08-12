package org.neg5.mappers;

import org.neg5.TournamentPlayerDTO;
import org.neg5.data.TournamentPlayer;

public class TournamentPlayerMapper extends AbstractObjectMapper<TournamentPlayer, TournamentPlayerDTO> {

    protected TournamentPlayerMapper() {
        super(TournamentPlayer.class, TournamentPlayerDTO.class);
    }

    @Override
    protected void addMappings() {
        getTypeMap().addMappings(mapper -> {
            mapper.map(player -> player.getTeam().getId(), TournamentPlayerDTO::setTeamId);
            mapper.map(player -> player.getTournament().getId(), TournamentPlayerDTO::setTournamentId);
        });
    }
}