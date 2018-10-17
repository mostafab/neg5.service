package org.neg5.managers.stats.aggregators;

import org.neg5.IndividualMatchStatsDTO;
import org.neg5.TournamentMatchDTO;

import java.util.ArrayList;
import java.util.List;

public class IndividualMatchesStatAggregator implements StatAggregator<List<IndividualMatchStatsDTO>> {

    private final String playerId;

    public IndividualMatchesStatAggregator(String playerId) {
        this.playerId = playerId;
    }

    @Override
    public void accept(TournamentMatchDTO match) {

    }

    @Override
    public List<IndividualMatchStatsDTO> collect() {
        return new ArrayList<>();
    }
}
