package org.neg5.managers.stats;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.neg5.managers.TournamentMatchManager;

@Singleton
public class IndividualStandingsStatsManager {

    @Inject private TournamentMatchManager tournamentMatchManager;
}
