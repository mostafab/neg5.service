package org.neg5;

import java.util.List;

public class TeamStandingsStatsDTO extends BaseAggregateStatsDTO {

    private List<TeamStandingStatsDTO> teamStandings;

    public List<TeamStandingStatsDTO> getTeamStandings() {
        return teamStandings;
    }

    public void setTeamStandings(List<TeamStandingStatsDTO> teamStandings) {
        this.teamStandings = teamStandings;
    }
}
