package org.neg5.mappers.data;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.neg5.MatchTeamDTO;
import org.neg5.data.transformers.data.TeamInMatch;
import org.neg5.mappers.AbstractObjectMapper;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Singleton
public class TeamInMatchMapper extends AbstractObjectMapper<TeamInMatch, MatchTeamDTO> {

    @Inject private TeamMatchPlayerMapper teamMatchPlayerMapper;

    protected TeamInMatchMapper() {
        super(TeamInMatch.class, MatchTeamDTO.class);
    }

    @Override
    protected void enrichDTO(MatchTeamDTO matchTeamDTO, TeamInMatch teamInMatch) {
        if (teamInMatch.getPlayers() == null) {
            matchTeamDTO.setPlayers(new ArrayList<>());
        } else {
            matchTeamDTO.setPlayers(teamInMatch.getPlayers().stream().map(teamMatchPlayerMapper::toDTO)
                    .collect(Collectors.toList()));
        }
        matchTeamDTO.setOvertimeTossupsGotten(teamInMatch.getOvertimeTossups());
    }
}
