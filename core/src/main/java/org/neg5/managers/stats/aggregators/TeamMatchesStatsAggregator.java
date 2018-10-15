package org.neg5.managers.stats.aggregators;

import org.neg5.TeamMatchStatsDTO;
import org.neg5.TournamentMatchDTO;

import java.util.ArrayList;
import java.util.List;

public class TeamMatchesStatsAggregator implements StatAggregator<List<TeamMatchStatsDTO>> {

    private final String teamId;

    public TeamMatchesStatsAggregator(String teamId) {
        this.teamId = teamId;
    }

    @Override
    public void accept(TournamentMatchDTO match) {

    }

    @Override
    public List<TeamMatchStatsDTO> collect() {
        return new ArrayList<>();
    }
}
