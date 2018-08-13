package org.neg5.mappers;

import org.neg5.MatchPlayerDTO;
import org.neg5.data.MatchPlayer;

public class MatchPlayerMapper extends AbstractObjectMapper<MatchPlayer, MatchPlayerDTO> {

    protected MatchPlayerMapper() {
        super(MatchPlayer.class, MatchPlayerDTO.class);
    }

    @Override
    protected void addMappings() {
        getTypeMap().addMappings(mapper -> {
            mapper.map(entity -> entity.getMatchPlayerId().getMatch().getId(), MatchPlayerDTO::setMatchId);
            mapper.map(entity -> entity.getMatchPlayerId().getPlayer().getId(), MatchPlayerDTO::setPlayerId);
        });
    }
}
