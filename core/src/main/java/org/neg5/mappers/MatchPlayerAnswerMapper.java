package org.neg5.mappers;

import com.google.inject.Singleton;
import org.neg5.MatchPlayerAnswerDTO;
import org.neg5.data.MatchPlayerAnswer;

@Singleton
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
           mapper.map(entity -> entity.getTournamentTossupValue().getAnswerType(), MatchPlayerAnswerDTO::setAnswerType);
        });
    }
}
