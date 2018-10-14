package org.neg5;

import java.util.Date;
import java.util.Set;

public class TournamentDTO {

    private String id;

    private String name;
    private Date tournamentDate;
    private String location;
    private String questionSet;
    private String comments;

    private Boolean usesBouncebacks;
    private Long bonusPointValue;
    private Long partsPerBonus;

    private String currentPhaseId;

    private Set<TournamentPhaseDTO> phases;
    private Set<TournamentDivisionDTO> divisions;
    private Set<TournamentTossupValueDTO> tossupValues;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTournamentDate() {
        return tournamentDate;
    }

    public void setTournamentDate(Date tournamentDate) {
        this.tournamentDate = tournamentDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getQuestionSet() {
        return questionSet;
    }

    public void setQuestionSet(String questionSet) {
        this.questionSet = questionSet;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Set<TournamentPhaseDTO> getPhases() {
        return phases;
    }

    public void setPhases(Set<TournamentPhaseDTO> phases) {
        this.phases = phases;
    }

    public Set<TournamentDivisionDTO> getDivisions() {
        return divisions;
    }

    public void setDivisions(Set<TournamentDivisionDTO> divisions) {
        this.divisions = divisions;
    }

    public Boolean getUsesBouncebacks() {
        return usesBouncebacks;
    }

    public void setUsesBouncebacks(Boolean usesBouncebacks) {
        this.usesBouncebacks = usesBouncebacks;
    }

    public Long getBonusPointValue() {
        return bonusPointValue;
    }

    public void setBonusPointValue(Long bonusPointValue) {
        this.bonusPointValue = bonusPointValue;
    }

    public Long getPartsPerBonus() {
        return partsPerBonus;
    }

    public void setPartsPerBonus(Long partsPerBonus) {
        this.partsPerBonus = partsPerBonus;
    }

    public Set<TournamentTossupValueDTO> getTossupValues() {
        return tossupValues;
    }

    public void setTossupValues(Set<TournamentTossupValueDTO> tossupValues) {
        this.tossupValues = tossupValues;
    }

    public String getCurrentPhaseId() {
        return currentPhaseId;
    }

    public void setCurrentPhaseId(String currentPhaseId) {
        this.currentPhaseId = currentPhaseId;
    }
}
