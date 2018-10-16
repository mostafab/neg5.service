package org.neg5;

import java.math.BigDecimal;

public class RoundStatDTO {

    private Integer round;
    private BigDecimal averagePointsPerGame;

    private Double tossupPoints;
    private Double tossupsHeard;

    private BigDecimal averagePointsPerBonus;

    public Integer getRound() {
        return round;
    }

    public void setRound(Integer round) {
        this.round = round;
    }

    public BigDecimal getAveragePointsPerGame() {
        return averagePointsPerGame;
    }

    public void setAveragePointsPerGame(BigDecimal averagePointsPerGame) {
        this.averagePointsPerGame = averagePointsPerGame;
    }

    public Double getTossupPoints() {
        return tossupPoints;
    }

    public void setTossupPoints(Double tossupPoints) {
        this.tossupPoints = tossupPoints;
    }

    public Double getTossupsHeard() {
        return tossupsHeard;
    }

    public void setTossupsHeard(Double tossupsHeard) {
        this.tossupsHeard = tossupsHeard;
    }

    public BigDecimal getAveragePointsPerBonus() {
        return averagePointsPerBonus;
    }

    public void setAveragePointsPerBonus(BigDecimal averagePointsPerBonus) {
        this.averagePointsPerBonus = averagePointsPerBonus;
    }
}
