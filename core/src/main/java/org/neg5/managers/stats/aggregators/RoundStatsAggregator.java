package org.neg5.managers.stats.aggregators;

import org.neg5.RoundStatDTO;
import org.neg5.TournamentMatchDTO;

public class RoundStatsAggregator implements StatAggregator<RoundStatDTO> {

    private final Integer round;

    public RoundStatsAggregator(Integer round) {
        this.round = round;
    }

    @Override
    public void accept(TournamentMatchDTO match) {

    }

    @Override
    public RoundStatDTO collect() {
        RoundStatDTO stat = new RoundStatDTO();
        stat.setRound(round);

        return stat;
    }
}
