package org.neg5.mappers.data;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.neg5.MatchTeamDTO;
import org.neg5.data.transformers.data.TeamInMatch;
import org.neg5.mappers.AbstractObjectMapper;

import java.util.stream.Collectors;

@Singleton
public class TeamInMatchMapper extends AbstractObjectMapper<TeamInMatch, MatchTeamDTO> {

    @Inject private TeamMatchPlayerMapper teamMatchPlayerMapper;

    protected TeamInMatchMapper() {
        super(TeamInMatch.class, MatchTeamDTO.class);
    }

    @Override
    public MatchTeamDTO toDTO(TeamInMatch teamInMatch) {
        MatchTeamDTO dto = super.toDTO(teamInMatch);
        dto.setPlayers(teamInMatch.getPlayers().stream().map(teamMatchPlayerMapper::toDTO)
                .collect(Collectors.toList()));
        return dto;
    }

    @Override
    protected void addMappings() {
        getTypeMap().addMappings(mapper -> {
            mapper.map(TeamInMatch::getOvertimeTossups, MatchTeamDTO::setOvertimeTossupsGotten);
        });
    }
}
