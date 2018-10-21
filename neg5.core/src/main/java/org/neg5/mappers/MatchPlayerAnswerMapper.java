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
    protected void enrichDTO(MatchPlayerAnswerDTO matchPlayerAnswerDTO, MatchPlayerAnswer matchPlayerAnswer) {
        matchPlayerAnswerDTO.setMatchId(matchPlayerAnswer.getId().getMatchId());
        matchPlayerAnswerDTO.setPlayerId(matchPlayerAnswer.getId().getPlayerId());
        matchPlayerAnswerDTO.setTossupValue(matchPlayerAnswer.getId().getTossupValue());
        matchPlayerAnswerDTO.setAnswerType(matchPlayerAnswer.getTournamentTossupValue().getAnswerType());
    }
}
