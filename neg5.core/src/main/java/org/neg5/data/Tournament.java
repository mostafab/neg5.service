package org.neg5.data;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "tournament")
@DynamicUpdate
public class Tournament extends AbstractDataObject<Tournament> implements IdDataObject<String> {

    private String id;

    private Account director;

    private String name;
    private Date tournamentDate;
    private String location;
    private String questionSet;
    private String comments;

    private Set<TournamentPhase> phases;
    private Set<TournamentDivision> divisions;
    private Set<TournamentTossupValue> tossupValues;

    private TournamentPhase currentPhase;

    private Boolean usesBouncebacks;
    private Long bonusPointValue;
    private Long partsPerBonus;

    @Id
    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @JoinColumn(name = "director_id", nullable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    public Account getDirector() {
        return director;
    }

    public void setDirector(Account director) {
        this.director = director;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "tournament_date")
    public Date getTournamentDate() {
        return tournamentDate;
    }

    public void setTournamentDate(Date tournamentDate) {
        this.tournamentDate = tournamentDate;
    }

    @Column(name = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Column(name = "question_set")
    public String getQuestionSet() {
        return questionSet;
    }

    public void setQuestionSet(String questionSet) {
        this.questionSet = questionSet;
    }

    @Column(name = "comments")
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tournament")
    public Set<TournamentPhase> getPhases() {
        return phases;
    }

    public void setPhases(Set<TournamentPhase> phases) {
        this.phases = phases;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tournament")
    public Set<TournamentDivision> getDivisions() {
        return divisions;
    }

    public void setDivisions(Set<TournamentDivision> divisions) {
        this.divisions = divisions;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id.tournament")
    public Set<TournamentTossupValue> getTossupValues() {
        return tossupValues;
    }

    public void setTossupValues(Set<TournamentTossupValue> tossupValues) {
        this.tossupValues = tossupValues;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "active_phase_id")
    @NotFound(action = NotFoundAction.IGNORE)
    public TournamentPhase getCurrentPhase() {
        return currentPhase;
    }

    public void setCurrentPhase(TournamentPhase currentPhase) {
        this.currentPhase = currentPhase;
    }

    @Column(name = "bouncebacks")
    public Boolean getUsesBouncebacks() {
        return usesBouncebacks;
    }

    public void setUsesBouncebacks(Boolean usesBouncebacks) {
        this.usesBouncebacks = usesBouncebacks;
    }

    @Column(name = "bonus_point_value")
    public Long getBonusPointValue() {
        return bonusPointValue;
    }

    public void setBonusPointValue(Long bonusPointValue) {
        this.bonusPointValue = bonusPointValue;
    }

    @Column(name = "parts_per_bonus")
    public Long getPartsPerBonus() {
        return partsPerBonus;
    }

    public void setPartsPerBonus(Long partsPerBonus) {
        this.partsPerBonus = partsPerBonus;
    }
}
