package org.neg5.data;

import org.hibernate.annotations.DynamicUpdate;
import org.neg5.ScoresheetCycleDTO;
import org.neg5.data.converters.ScoresheetCycleConverter;
import org.neg5.data.converters.ScoresheetCycleListConverter;
import org.neg5.data.converters.SetOfStringsConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "scoresheet")
@DynamicUpdate
@SequenceGenerator(name = Scoresheet.ID_SEQ, sequenceName = Scoresheet.ID_SEQ, allocationSize = 1)
public class Scoresheet extends AbstractDataObject<Scoresheet> implements IdDataObject<Long>, SpecificTournamentEntity {

    static final String ID_SEQ = "scoresheet_id_seq";

    private Long id;

    private Tournament tournament;

    private String moderator;
    private String notes;
    private boolean onTossup;
    private String packet;
    private String room;
    private Integer round;

    private Boolean submitted;

    private Set<String> phases;
    private List<ScoresheetCycleDTO> cycles;
    private ScoresheetCycleDTO currentCycle;

    @Id
    @Override
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = Scoresheet.ID_SEQ)
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    @JoinColumn(name = "tournament_id", nullable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    @Column(name = "moderator")
    public String getModerator() {
        return moderator;
    }

    public void setModerator(String moderator) {
        this.moderator = moderator;
    }

    @Column(name = "notes")
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Column(name = "on_tossup")
    public boolean getIsOnTossup() {
        return onTossup;
    }

    public void setIsOnTossup(boolean onTossup) {
        this.onTossup = onTossup;
    }

    @Column(name = "packet")
    public String getPacket() {
        return packet;
    }

    public void setPacket(String packet) {
        this.packet = packet;
    }

    @Column(name = "room")
    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    @Column(name = "round")
    public Integer getRound() {
        return round;
    }

    public void setRound(Integer round) {
        this.round = round;
    }

    @Column(name = "submitted")
    public Boolean getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Boolean submitted) {
        this.submitted = submitted;
    }

    @Column(name = "phases")
    @Convert(converter = SetOfStringsConverter.class)
    public Set<String> getPhases() {
        return phases;
    }

    public void setPhases(Set<String> phases) {
        this.phases = phases;
    }

    @Column(name = "cycles")
    @Convert(converter = ScoresheetCycleListConverter.class)
    public List<ScoresheetCycleDTO> getCycles() {
        return cycles;
    }

    public void setCycles(List<ScoresheetCycleDTO> cycles) {
        this.cycles = cycles;
    }

    @Column(name = "current_cycle")
    @Convert(converter = ScoresheetCycleConverter.class)
    public ScoresheetCycleDTO getCurrentCycle() {
        return currentCycle;
    }

    public void setCurrentCycle(ScoresheetCycleDTO currentCycle) {
        this.currentCycle = currentCycle;
    }
}
