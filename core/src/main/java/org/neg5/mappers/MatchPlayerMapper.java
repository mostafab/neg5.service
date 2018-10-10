package org.neg5.mappers;

import com.google.inject.Inject;
import org.neg5.MatchPlayerDTO;
import org.neg5.data.MatchPlayer;

import java.util.stream.Collectors;

public class MatchPlayerMapper extends AbstractObjectMapper<MatchPlayer, MatchPlayerDTO> {

    @Inject private MatchPlayerAnswerMapper matchPlayerAnswerMapper;

    protected MatchPlayerMapper() {
        super(MatchPlayer.class, MatchPlayerDTO.class);
    }

    @Override
    public MatchPlayerDTO toDTO(MatchPlayer matchPlayer) {
        MatchPlayerDTO dto = super.toDTO(matchPlayer);
        dto.setAnswers(matchPlayer.getAnswers().stream()
            .map(answer -> matchPlayerAnswerMapper.toDTO(answer)).collect(Collectors.toSet()));
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
