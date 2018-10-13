package org.neg5.managers.stats;

import org.neg5.TournamentMatchDTO;

interface StatAggregator {

    void accept(TournamentMatchDTO match);
}
