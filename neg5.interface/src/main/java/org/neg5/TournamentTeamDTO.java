package org.neg5;

import java.util.Set;

public class TournamentTeamDTO {

    private String id;
    private String name;
    private String tournamentId;

    private Set<TournamentDivisionDTO> divisions;
    private Set<TournamentPlayerDTO> players;

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

    public String getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(String tournamentId) {
        this.tournamentId = tournamentId;
    }

    public Set<TournamentDivisionDTO> getDivisions() {
        return divisions;
    }

    public void setDivisions(Set<TournamentDivisionDTO> divisions) {
        this.divisions = divisions;
    }

    public Set<TournamentPlayerDTO> getPlayers() {
        return players;
    }

    public void setPlayers(Set<TournamentPlayerDTO> players) {
        this.players = players;
    }
}
