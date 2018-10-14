package org.neg5.mappers;

import org.neg5.MatchPlayerAnswerDTO;
import org.neg5.data.MatchPlayerAnswer;
import org.neg5.data.Tournament;

public class MatchPlayerAnswerMapper extends AbstractObjectMapper<MatchPlayerAnswer, MatchPlayerAnswerDTO> {

    protected MatchPlayerAnswerMapper() {
        super(MatchPlayerAnswer.class, MatchPlayerAnswerDTO.class);
    }

    @Override
    public MatchPlayerAnswerDTO toDTO(MatchPlayerAnswer matchPlayerAnswer) {
        MatchPlayerAnswerDTO answer = super.toDTO(matchPlayerAnswer);
        setAnswerType(matchPlayerAnswer, answer);
        return answer;
    }

    @Override
    protected void addMappings() {
        getTypeMap().addMappings(mapper -> {
           mapper.map(entity -> entity.getMatchPlayerAnswerId().getMatch().getId(), MatchPlayerAnswerDTO::setMatchId);
           mapper.map(entity -> entity.getMatchPlayerAnswerId().getPlayer().getId(), MatchPlayerAnswerDTO::setPlayerId);
           mapper.map(entity -> entity.getMatchPlayerAnswerId().getTossupValue(), MatchPlayerAnswerDTO::setTossupValue);
        });
    }

    private void setAnswerType(MatchPlayerAnswer matchPlayerAnswer, MatchPlayerAnswerDTO dto) {
        Tournament tournament = matchPlayerAnswer.getMatchPlayerAnswerId().getTournament();
        tournament.getTossupValues().stream()
                .filter(tv -> tv.getTournamentTossupValueId().getValue() != null)
                .filter(tv -> tv.getTournamentTossupValueId().getValue().equals(dto.getTossupValue()))
                .findFirst()
                .ifPresent(tv -> dto.setAnswerType(tv.getAnswerType()));
    }
}
