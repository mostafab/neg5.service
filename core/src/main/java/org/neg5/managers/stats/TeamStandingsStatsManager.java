package org.neg5.managers.stats;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.neg5.TeamStandingStatsDTO;
import org.neg5.TeamStandingsStatsDTO;
import org.neg5.TournamentMatchDTO;
import org.neg5.managers.TournamentTeamManager;
import org.neg5.managers.stats.aggregators.TeamStandingStatAggregator;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Singleton
public class TeamStandingsStatsManager {

    @Inject private TournamentTeamManager tournamentTeamManager;

    public TeamStandingsStatsDTO calculateTeamStandings(String tournamentId, String phaseId) {
        TeamStandingsStatsDTO stats = new TeamStandingsStatsDTO();
        stats.setTournamentId(tournamentId);
        stats.setPhaseId(phaseId);

        Map<String, List<TournamentMatchDTO>> teamsByMatches = tournamentTeamManager
                .groupTeamsByMatches(tournamentId, phaseId);
        stats.setTeamStandings(
            teamsByMatches.entrySet().stream()
                .map(entry -> computeStandingsForTeam(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList())
        );

        return stats;
    }

    private TeamStandingStatsDTO computeStandingsForTeam(String teamId, List<TournamentMatchDTO> matches) {
        TeamStandingStatAggregator aggregator = new TeamStandingStatAggregator(teamId);
        matches.forEach(aggregator::accept);
        return aggregator.collect();
    }
}
