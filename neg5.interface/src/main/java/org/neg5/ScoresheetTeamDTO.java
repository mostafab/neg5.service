package org.neg5;

import java.util.List;

public class ScoresheetTeamDTO {

    private String teamId;
    private List<ScoresheetPlayerDTO> players;

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public List<ScoresheetPlayerDTO> getPlayers() {
        return players;
    }

    public void setPlayers(List<ScoresheetPlayerDTO> players) {
        this.players = players;
    }
}
