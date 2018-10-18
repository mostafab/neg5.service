package org.neg5;

import java.util.List;

public class IndividualStandingsStatsDTO extends BaseAggregateStatsDTO {

    private List<IndividualStandingStatDTO> playerStandings;

    public List<IndividualStandingStatDTO> getPlayerStandings() {
        return playerStandings;
    }

    public void setPlayerStandings(List<IndividualStandingStatDTO> playerStandings) {
        this.playerStandings = playerStandings;
    }
}
