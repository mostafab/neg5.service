package org.neg5.mappers.data;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.neg5.MatchPlayerAnswerDTO;
import org.neg5.data.transformers.data.TeamMatchPlayerAnswer;

@ExtendWith(MockitoExtension.class)
public class TeamMatchPlayerAnswerMapperTest {

    @InjectMocks private TeamMatchPlayerAnswerMapper teamMatchPlayerAnswerMapper;

    @Test
    public void testMapsToDtoCorrectly() {
        TeamMatchPlayerAnswer answer = buildTeamMatchPlayerAnswer();

        MatchPlayerAnswerDTO dto = teamMatchPlayerAnswerMapper.toDTO(answer);

        Assert.assertEquals(answer.getMatchId(), dto.getMatchId());
        Assert.assertEquals(answer.getNumber(), dto.getNumberGotten());
        Assert.assertEquals(answer.getPlayerId(), dto.getPlayerId());
        Assert.assertEquals(answer.getValue(), dto.getTossupValue());
    }

    private TeamMatchPlayerAnswer buildTeamMatchPlayerAnswer() {
        TeamMatchPlayerAnswer answer = new TeamMatchPlayerAnswer();
        answer.setMatchId("MATCH_ID");
        answer.setNumber(1);
        answer.setPlayerId("PLAYER_ID");
        answer.setValue(10);

        return answer;
    }
}
