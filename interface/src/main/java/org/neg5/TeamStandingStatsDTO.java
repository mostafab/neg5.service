package org.neg5;

import java.math.BigDecimal;
import java.util.Map;

public class TeamStandingStatsDTO {

    private String teamId;

    private BigDecimal pointsPerGame;
    private BigDecimal pointsAgainstPerGame;
    private BigDecimal marginOfVictory;

    private TeamRecordDTO record;

    private Map<Integer, Long> tossupAnswerCounts;

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public BigDecimal getPointsPerGame() {
        return pointsPerGame;
    }

    public void setPointsPerGame(BigDecimal pointsPerGame) {
        this.pointsPerGame = pointsPerGame;
    }

    public BigDecimal getPointsAgainstPerGame() {
        return pointsAgainstPerGame;
    }

    public void setPointsAgainstPerGame(BigDecimal pointsAgainstPerGame) {
        this.pointsAgainstPerGame = pointsAgainstPerGame;
    }

    public Map<Integer, Long> getTossupAnswerCounts() {
        return tossupAnswerCounts;
    }

    public void setTossupAnswerCounts(Map<Integer, Long> tossupAnswerCounts) {
        this.tossupAnswerCounts = tossupAnswerCounts;
    }

    public BigDecimal getMarginOfVictory() {
        return marginOfVictory;
    }

    public void setMarginOfVictory(BigDecimal marginOfVictory) {
        this.marginOfVictory = marginOfVictory;
    }

    public TeamRecordDTO getRecord() {
        return record;
    }

    public void setRecord(TeamRecordDTO record) {
        this.record = record;
    }
}
