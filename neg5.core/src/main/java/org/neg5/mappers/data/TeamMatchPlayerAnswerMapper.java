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
    public MatchPlayerAnswerDTO toDTO(TeamMatchPlayerAnswer teamMatchPlayerAnswer) {
        MatchPlayerAnswerDTO dto = super.toDTO(teamMatchPlayerAnswer);
        return dto;
    }

    @Override
    protected void addMappings() {
        getTypeMap().addMappings(mapper -> {
           mapper.map(TeamMatchPlayerAnswer::getNumber, MatchPlayerAnswerDTO::setNumberGotten);
           mapper.map(TeamMatchPlayerAnswer::getValue, MatchPlayerAnswerDTO::setTossupValue);
        });
    }
}
