package org.neg5.data.transformers.data;

import java.util.Set;

public class Match {

    private String id;
    private String tournamentId;

    private Integer round;
    private Integer tossupsHeard;

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

    public Integer getRound() {
        return round;
    }

    public void setRound(Integer round) {
        this.round = round;
    }

    public Integer getTossupsHeard() {
        return tossupsHeard;
    }

    public void setTossupsHeard(Integer tossupsHeard) {
        this.tossupsHeard = tossupsHeard;
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
