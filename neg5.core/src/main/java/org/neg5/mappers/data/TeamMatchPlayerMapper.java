package org.neg5.mappers.data;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.neg5.MatchPlayerDTO;
import org.neg5.data.transformers.data.TeamMatchPlayer;
import org.neg5.mappers.AbstractObjectMapper;

import java.util.HashSet;
import java.util.stream.Collectors;

@Singleton
public class TeamMatchPlayerMapper extends AbstractObjectMapper<TeamMatchPlayer, MatchPlayerDTO> {

    @Inject private TeamMatchPlayerAnswerMapper teamMatchPlayerAnswerMapper;

    protected TeamMatchPlayerMapper() {
        super(TeamMatchPlayer.class, MatchPlayerDTO.class);
    }

    @Override
    protected void enrichDTO(MatchPlayerDTO matchPlayerDTO, TeamMatchPlayer teamMatchPlayer) {
        if (teamMatchPlayer.getTossupValues() != null) {
            matchPlayerDTO.setAnswers(teamMatchPlayer.getTossupValues().stream()
                    .map(teamMatchPlayerAnswerMapper::toDTO).collect(Collectors.toSet()));
        } else {
            matchPlayerDTO.setAnswers(new HashSet<>());
        }
    }
}
