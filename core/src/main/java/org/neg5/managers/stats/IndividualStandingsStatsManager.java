package org.neg5.managers.stats;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.neg5.IndividualStandingsStatsDTO;
import org.neg5.TournamentMatchDTO;
import org.neg5.managers.TournamentPlayerManager;

import java.util.List;
import java.util.Map;

@Singleton
public class IndividualStandingsStatsManager {

    @Inject private TournamentPlayerManager tournamentPlayerManager;

    public IndividualStandingsStatsDTO calculateIndividualStandings(String tournamentId,
                                                                    String phaseId) {
        Map<String, List<TournamentMatchDTO>> matchesByPlayers = tournamentPlayerManager
                .groupPlayersByMatches(tournamentId, phaseId);
        return new IndividualStandingsStatsDTO();
    }
}
