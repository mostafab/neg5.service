package org.neg5.mappers;

import org.neg5.MatchPlayerAnswerDTO;
import org.neg5.data.MatchPlayerAnswer;

public class MatchPlayerAnswerMapper extends AbstractObjectMapper<MatchPlayerAnswer, MatchPlayerAnswerDTO> {

    protected MatchPlayerAnswerMapper() {
        super(MatchPlayerAnswer.class, MatchPlayerAnswerDTO.class);
    }

    @Override
    protected void addMappings() {
        getTypeMap().addMappings(mapper -> {
           mapper.map(entity -> entity.getMatchPlayerAnswerId().getMatch().getId(), MatchPlayerAnswerDTO::setMatchId);
           mapper.map(entity -> entity.getMatchPlayerAnswerId().getPlayer().getId(), MatchPlayerAnswerDTO::setPlayerId);
           mapper.map(entity -> entity.getMatchPlayerAnswerId().getTossupValue(), MatchPlayerAnswerDTO::setTossupValue);
        });
    }
}
