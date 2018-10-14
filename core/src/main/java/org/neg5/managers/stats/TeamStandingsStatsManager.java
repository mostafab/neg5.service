package org.neg5.managers.stats;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import org.neg5.MatchTeamDTO;
import org.neg5.TeamStandingStatsDTO;
import org.neg5.TeamStandingsStatsDTO;
import org.neg5.TournamentMatchDTO;
import org.neg5.managers.TournamentManager;
import org.neg5.managers.TournamentMatchManager;
import org.neg5.managers.stats.aggregators.TeamStandingStatAggregator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class TeamStandingsStatsManager {

    @Inject private TournamentMatchManager tournamentMatchManager;
    @Inject private TournamentManager tournamentManager;

    public TeamStandingsStatsDTO calculate(String tournamentId, String phaseId) {
        List<TournamentMatchDTO> matches = tournamentMatchManager.findAllByTournamentAndPhase(tournamentId, phaseId);

        TeamStandingsStatsDTO stats = new TeamStandingsStatsDTO();
        stats.setTournamentId(tournamentId);
        stats.setPhaseId(phaseId);

        Map<String, List<TournamentMatchDTO>> teamsByMatches = groupTeamsByMatches(matches);
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

    private Map<String, List<TournamentMatchDTO>> groupTeamsByMatches(List<TournamentMatchDTO> matches) {
        Map<String, List<TournamentMatchDTO>> matchesByTeamId = new HashMap<>();
        matches.forEach(match -> {
            Set<MatchTeamDTO> teams = match.getTeams();
            teams.forEach(team -> {
                matchesByTeamId.computeIfPresent(team.getTeamId(), (id, list) -> {
                    list.add(match);
                    return list;
                });
                matchesByTeamId.computeIfAbsent(team.getTeamId(), teamId -> Lists.newArrayList(match));
            });
        });
        return matchesByTeamId;
    }
}
