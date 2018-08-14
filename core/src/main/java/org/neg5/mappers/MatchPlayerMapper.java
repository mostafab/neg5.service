package org.neg5.mappers;

import com.google.inject.Inject;
import org.neg5.MatchPlayerAnswerDTO;
import org.neg5.MatchPlayerDTO;
import org.neg5.data.MatchPlayer;

import java.util.List;
import java.util.stream.Collectors;

public class MatchPlayerMapper extends AbstractObjectMapper<MatchPlayer, MatchPlayerDTO> {

    @Inject private MatchPlayerAnswerMapper matchPlayerAnswerMapper;

    protected MatchPlayerMapper() {
        super(MatchPlayer.class, MatchPlayerDTO.class);
    }

    @Override
    public MatchPlayerDTO toDTO(MatchPlayer matchPlayer) {
        MatchPlayerDTO dto = super.toDTO(matchPlayer);
        List<MatchPlayerAnswerDTO> playerAnswers = matchPlayer.getMatchPlayerId().getMatch().getPlayerAnswers()
                .stream()
                .filter(answer -> answer.getMatchPlayerAnswerId().getPlayer().getId().equals(dto.getPlayerId()))
                .map(matchPlayerAnswerMapper::toDTO)
                .collect(Collectors.toList());
        dto.setAnswers(playerAnswers);
        return dto;
    }

    @Override
    protected void addMappings() {
        getTypeMap().addMappings(mapper -> {
            mapper.map(entity -> entity.getMatchPlayerId().getMatch().getId(), MatchPlayerDTO::setMatchId);
            mapper.map(entity -> entity.getMatchPlayerId().getPlayer().getId(), MatchPlayerDTO::setPlayerId);
        });
    }
}
