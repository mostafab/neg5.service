package org.neg5;

import org.neg5.enums.TossupAnswerType;

public class ScoresheetCycleAnswerDTO {

    private String playerId;
    private String teamId;
    private TossupAnswerType type;
    private Integer value;

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public TossupAnswerType getType() {
        return type;
    }

    public void setType(TossupAnswerType type) {
        this.type = type;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
