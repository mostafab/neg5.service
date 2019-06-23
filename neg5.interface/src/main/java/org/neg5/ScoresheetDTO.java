package org.neg5;

import java.util.List;
import java.util.Set;

public class ScoresheetDTO {

    private Long id;

    private String tournamentId;

    private String moderator;
    private String notes;
    private boolean onTossup;
    private String packet;
    private String room;
    private Integer round;

    private Boolean submitted;

    private Set<String> phases;

    private List<ScoresheetTeamDTO> teams;
    private List<ScoresheetCycleDTO> cycles;

    private ScoresheetCycleDTO currentCycle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(String tournamentId) {
        this.tournamentId = tournamentId;
    }

    public String getModerator() {
        return moderator;
    }

    public void setModerator(String moderator) {
        this.moderator = moderator;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean getIsOnTossup() {
        return onTossup;
    }

    public void setIsOnTossup(boolean onTossup) {
        this.onTossup = onTossup;
    }

    public String getPacket() {
        return packet;
    }

    public void setPacket(String packet) {
        this.packet = packet;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Integer getRound() {
        return round;
    }

    public void setRound(Integer round) {
        this.round = round;
    }

    public Boolean getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Boolean submitted) {
        this.submitted = submitted;
    }

    public Set<String> getPhases() {
        return phases;
    }

    public void setPhases(Set<String> phases) {
        this.phases = phases;
    }

    public List<ScoresheetTeamDTO> getTeams() {
        return teams;
    }

    public void setTeams(List<ScoresheetTeamDTO> teams) {
        this.teams = teams;
    }

    public List<ScoresheetCycleDTO> getCycles() {
        return cycles;
    }

    public void setCycles(List<ScoresheetCycleDTO> cycles) {
        this.cycles = cycles;
    }

    public ScoresheetCycleDTO getCurrentCycle() {
        return currentCycle;
    }

    public void setCurrentCycle(ScoresheetCycleDTO currentCycle) {
        this.currentCycle = currentCycle;
    }
}
