package org.neg5.mappers.data;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.neg5.MatchPlayerDTO;
import org.neg5.data.transformers.data.TeamMatchPlayer;
import org.neg5.mappers.AbstractObjectMapper;

import java.util.stream.Collectors;

@Singleton
public class TeamMatchPlayerMapper extends AbstractObjectMapper<TeamMatchPlayer, MatchPlayerDTO> {

    @Inject private TeamMatchPlayerAnswerMapper teamMatchPlayerAnswerMapper;

    protected TeamMatchPlayerMapper() {
        super(TeamMatchPlayer.class, MatchPlayerDTO.class);
    }

    @Override
    public MatchPlayerDTO toDTO(TeamMatchPlayer teamMatchPlayer) {
        MatchPlayerDTO player = super.toDTO(teamMatchPlayer);
        player.setAnswers(teamMatchPlayer.getTossupValues().stream()
                .map(teamMatchPlayerAnswerMapper::toDTO).collect(Collectors.toSet()));
        return player;
    }
}
