package org.neg5.data.transformers.data;

import java.util.Set;

public class Match {

    private String id;
    private String tournamentId;
    private Set<Phase> phases;
    private Set<TeamInMatch> teams;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(String tournamentId) {
        this.tournamentId = tournamentId;
    }

    public Set<Phase> getPhases() {
        return phases;
    }

    public void setPhases(Set<Phase> phases) {
        this.phases = phases;
    }

    public Set<TeamInMatch> getTeams() {
        return teams;
    }

    public void setTeams(Set<TeamInMatch> teams) {
        this.teams = teams;
    }
}
