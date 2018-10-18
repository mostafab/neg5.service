package org.neg5;

import java.util.List;

public class FullTeamsMatchesStatsDTO extends BaseAggregateStatsDTO {

    private List<TeamMatchesStatsDTO> teams;

    public List<TeamMatchesStatsDTO> getTeams() {
        return teams;
    }

    public void setTeams(List<TeamMatchesStatsDTO> teams) {
        this.teams = teams;
    }
}
