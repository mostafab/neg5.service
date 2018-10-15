package org.neg5;

import java.math.BigDecimal;
import java.util.Set;

public class IndividualStandingStatDTO {

    private String playerId;

    private BigDecimal gamesPlayed;
    private Set<AnswersDTO> tossupAnswerCounts;
    private int tossupsHeard;
    private int totalPoints;

    private BigDecimal pointsPerTossupHeard;
    private BigDecimal pointsPerGame;

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public BigDecimal getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(BigDecimal gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public Set<AnswersDTO> getTossupAnswerCounts() {
        return tossupAnswerCounts;
    }

    public void setTossupAnswerCounts(Set<AnswersDTO> tossupAnswerCounts) {
        this.tossupAnswerCounts = tossupAnswerCounts;
    }

    public int getTossupsHeard() {
        return tossupsHeard;
    }

    public void setTossupsHeard(int tossupsHeard) {
        this.tossupsHeard = tossupsHeard;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public BigDecimal getPointsPerTossupHeard() {
        return pointsPerTossupHeard;
    }

    public void setPointsPerTossupHeard(BigDecimal pointsPerTossupHeard) {
        this.pointsPerTossupHeard = pointsPerTossupHeard;
    }

    public BigDecimal getPointsPerGame() {
        return pointsPerGame;
    }

    public void setPointsPerGame(BigDecimal pointsPerGame) {
        this.pointsPerGame = pointsPerGame;
    }
}
