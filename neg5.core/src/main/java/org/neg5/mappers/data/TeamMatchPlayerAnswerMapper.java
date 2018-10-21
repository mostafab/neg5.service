package org.neg5.mappers.data;

import com.google.inject.Singleton;
import org.neg5.MatchPlayerAnswerDTO;
import org.neg5.data.transformers.data.TeamMatchPlayerAnswer;
import org.neg5.mappers.AbstractObjectMapper;

@Singleton
public class TeamMatchPlayerAnswerMapper extends AbstractObjectMapper<TeamMatchPlayerAnswer, MatchPlayerAnswerDTO> {

    protected TeamMatchPlayerAnswerMapper() {
        super(TeamMatchPlayerAnswer.class, MatchPlayerAnswerDTO.class);
    }

    @Override
    protected void enrichDTO(MatchPlayerAnswerDTO matchPlayerAnswerDTO, TeamMatchPlayerAnswer teamMatchPlayerAnswer) {
        matchPlayerAnswerDTO.setNumberGotten(teamMatchPlayerAnswer.getNumber());
        matchPlayerAnswerDTO.setTossupValue(teamMatchPlayerAnswer.getValue());
    }
}
