package org.neg5.managers.stats;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.neg5.FullIndividualMatchesStatsDTO;
import org.neg5.IndividualMatchesStatsDTO;
import org.neg5.IndividualStandingStatDTO;
import org.neg5.IndividualStandingsStatsDTO;
import org.neg5.TournamentMatchDTO;
import org.neg5.managers.TournamentPlayerManager;
import org.neg5.managers.stats.aggregators.IndividualStandingStatAggregator;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Singleton
public class IndividualStandingsStatsManager {

    @Inject private TournamentPlayerManager tournamentPlayerManager;

    public IndividualStandingsStatsDTO calculateIndividualStandings(String tournamentId,
                                                                    String phaseId) {
        IndividualStandingsStatsDTO stats = new IndividualStandingsStatsDTO();
        stats.setTournamentId(tournamentId);
        stats.setPhaseId(phaseId);

        Map<String, List<TournamentMatchDTO>> matchesByPlayers = tournamentPlayerManager
                .groupPlayersByMatches(tournamentId, phaseId);

        stats.setPlayerStandings(
                matchesByPlayers.entrySet().stream()
                        .map(entry -> computeStandingsForPlayer(entry.getKey(), entry.getValue()))
                        .collect(Collectors.toList())
        );

        return stats;
    }

    public FullIndividualMatchesStatsDTO getFullIndividualStats(String tournamentId,
                                                                String phaseId) {
        FullIndividualMatchesStatsDTO stats = new FullIndividualMatchesStatsDTO();
        stats.setTournamentId(tournamentId);
        stats.setPhaseId(phaseId);

        Map<String, List<TournamentMatchDTO>> matchesByPlayers = tournamentPlayerManager
                .groupPlayersByMatches(tournamentId, phaseId);

        stats.setPlayers(matchesByPlayers.entrySet().stream()
            .map(entry -> computeSinglePlayerFullResults(entry.getKey(), entry.getValue()))
            .collect(Collectors.toList())
        );
        return stats;
    }

    private IndividualStandingStatDTO computeStandingsForPlayer(String playerId, List<TournamentMatchDTO> matches) {
        IndividualStandingStatAggregator aggregator = new IndividualStandingStatAggregator(playerId);
        matches.forEach(aggregator::accept);
        return aggregator.collect();
    }

    private IndividualMatchesStatsDTO computeSinglePlayerFullResults(String playerId,
                                                                     List<TournamentMatchDTO> matches) {
        IndividualMatchesStatsDTO stats = new IndividualMatchesStatsDTO();
        stats.setPlayerId(playerId);

        return stats;
    }
}
