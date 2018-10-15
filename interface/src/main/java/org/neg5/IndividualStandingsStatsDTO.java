package org.neg5;

import java.util.List;

public class IndividualStandingsStatsDTO extends BaseAggregateStatsDTO {

    private List<IndividualStandingStatDTO> teamStandings;

    public List<IndividualStandingStatDTO> getTeamStandings() {
        return teamStandings;
    }

    public void setTeamStandings(List<IndividualStandingStatDTO> teamStandings) {
        this.teamStandings = teamStandings;
    }
}
