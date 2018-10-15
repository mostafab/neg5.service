package org.neg5;

import java.math.BigDecimal;
import java.util.Set;

public class TeamMatchStatsDTO {

    private String opponentTeamId;
    private int round;

    private double points;
    private double opponentPoints;

    private Set<AnswersDTO> tossupAnswerCounts;
    private double tossupsHeard;
    private BigDecimal pointsPerTossupHeard;

    private BigDecimal powersToNegRatio;
    private BigDecimal getsToNegRatio;

    private int bonusesHeard;
    private double bonusPoints;

    private BigDecimal pointsPerBonus;

    public String getOpponentTeamId() {
        return opponentTeamId;
    }

    public void setOpponentTeamId(String opponentTeamId) {
        this.opponentTeamId = opponentTeamId;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public double getOpponentPoints() {
        return opponentPoints;
    }

    public void setOpponentPoints(double opponentPoints) {
        this.opponentPoints = opponentPoints;
    }

    public Set<AnswersDTO> getTossupAnswerCounts() {
        return tossupAnswerCounts;
    }

    public void setTossupAnswerCounts(Set<AnswersDTO> tossupAnswerCounts) {
        this.tossupAnswerCounts = tossupAnswerCounts;
    }

    public double getTossupsHeard() {
        return tossupsHeard;
    }

    public void setTossupsHeard(double tossupsHeard) {
        this.tossupsHeard = tossupsHeard;
    }

    public BigDecimal getPointsPerTossupHeard() {
        return pointsPerTossupHeard;
    }

    public void setPointsPerTossupHeard(BigDecimal pointsPerTossupHeard) {
        this.pointsPerTossupHeard = pointsPerTossupHeard;
    }

    public BigDecimal getPowersToNegRatio() {
        return powersToNegRatio;
    }

    public void setPowersToNegRatio(BigDecimal powersToNegRatio) {
        this.powersToNegRatio = powersToNegRatio;
    }

    public BigDecimal getGetsToNegRatio() {
        return getsToNegRatio;
    }

    public void setGetsToNegRatio(BigDecimal getsToNegRatio) {
        this.getsToNegRatio = getsToNegRatio;
    }

    public int getBonusesHeard() {
        return bonusesHeard;
    }

    public void setBonusesHeard(int bonusesHeard) {
        this.bonusesHeard = bonusesHeard;
    }

    public double getBonusPoints() {
        return bonusPoints;
    }

    public void setBonusPoints(double bonusPoints) {
        this.bonusPoints = bonusPoints;
    }

    public BigDecimal getPointsPerBonus() {
        return pointsPerBonus;
    }

    public void setPointsPerBonus(BigDecimal pointsPerBonus) {
        this.pointsPerBonus = pointsPerBonus;
    }
}
