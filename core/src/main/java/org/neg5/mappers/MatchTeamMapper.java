package org.neg5.mappers;

import org.neg5.MatchTeamDTO;
import org.neg5.data.MatchTeam;

public class MatchTeamMapper extends AbstractObjectMapper<MatchTeam, MatchTeamDTO> {

    protected MatchTeamMapper() {
        super(MatchTeam.class, MatchTeamDTO.class);
    }

    @Override
    protected void addMappings() {
        getTypeMap().addMappings(mapper -> {
           mapper.map(entity -> entity.getMatchTeamId().getMatch().getId(), MatchTeamDTO::setMatchId);
           mapper.map(entity -> entity.getMatchTeamId().getTeam().getId(), MatchTeamDTO::setTeamId);
        });
    }
}
