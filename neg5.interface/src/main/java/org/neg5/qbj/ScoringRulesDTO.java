package org.neg5.qbj;

import java.util.List;

public class ScoringRulesDTO {

    private Integer teamsPerMatch;
    private Integer maximumPlayersPerTeam;

    private Integer regulationTossupCount;
    private Integer maximumBonusScore;

    private Integer minimumPartsPerBonus;

    private Boolean bonusesBounceBack;

    private List<AnswerTypeDTO> answerTypes;

    public Integer getTeamsPerMatch() {
        return teamsPerMatch;
    }

    public void setTeamsPerMatch(Integer teamsPerMatch) {
        this.teamsPerMatch = teamsPerMatch;
    }

    public Integer getMaximumPlayersPerTeam() {
        return maximumPlayersPerTeam;
    }

    public void setMaximumPlayersPerTeam(Integer maximumPlayersPerTeam) {
        this.maximumPlayersPerTeam = maximumPlayersPerTeam;
    }

    public Integer getRegulationTossupCount() {
        return regulationTossupCount;
    }

    public void setRegulationTossupCount(Integer regulationTossupCount) {
        this.regulationTossupCount = regulationTossupCount;
    }

    public Integer getMaximumBonusScore() {
        return maximumBonusScore;
    }

    public void setMaximumBonusScore(Integer maximumBonusScore) {
        this.maximumBonusScore = maximumBonusScore;
    }

    public Integer getMinimumPartsPerBonus() {
        return minimumPartsPerBonus;
    }

    public void setMinimumPartsPerBonus(Integer minimumPartsPerBonus) {
        this.minimumPartsPerBonus = minimumPartsPerBonus;
    }

    public Boolean getBonusesBounceBack() {
        return bonusesBounceBack;
    }

    public void setBonusesBounceBack(Boolean bonusesBounceBack) {
        this.bonusesBounceBack = bonusesBounceBack;
    }

    public List<AnswerTypeDTO> getAnswerTypes() {
        return answerTypes;
    }

    public void setAnswerTypes(List<AnswerTypeDTO> answerTypes) {
        this.answerTypes = answerTypes;
    }
}
