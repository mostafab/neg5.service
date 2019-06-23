package org.neg5;

import java.util.List;

public class ScoresheetCycleDTO {

    private int number;
    private List<ScoresheetCycleAnswerDTO> answers;
    private List<ScoresheetCycleBonusDTO> bonuses;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<ScoresheetCycleAnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<ScoresheetCycleAnswerDTO> answers) {
        this.answers = answers;
    }

    public List<ScoresheetCycleBonusDTO> getBonuses() {
        return bonuses;
    }

    public void setBonuses(List<ScoresheetCycleBonusDTO> bonuses) {
        this.bonuses = bonuses;
    }
}
