package org.neg5;

import java.util.List;

public class MatchPlayerDTO {

    private String playerId;
    private String matchId;

    private Integer tossupsHeard;

    private List<MatchPlayerAnswerDTO> answers;

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public Integer getTossupsHeard() {
        return tossupsHeard;
    }

    public void setTossupsHeard(Integer tossupsHeard) {
        this.tossupsHeard = tossupsHeard;
    }

    public List<MatchPlayerAnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<MatchPlayerAnswerDTO> answers) {
        this.answers = answers;
    }
}
