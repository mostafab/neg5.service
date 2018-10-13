package org.neg5.managers.stats;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import org.neg5.MatchTeamDTO;
import org.neg5.TeamRecordDTO;
import org.neg5.TeamStandingStatsDTO;
import org.neg5.TeamStandingsStatsDTO;
import org.neg5.TournamentMatchDTO;
import org.neg5.managers.TournamentManager;
import org.neg5.managers.TournamentMatchManager;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
        return aggregator.aggregate();
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

    private TeamRecordDTO getRecord(String teamId, List<TournamentMatchDTO> matches) {
        TeamRecordDTO record = new TeamRecordDTO();
        matches.forEach(match -> {
            Optional<MatchTeamDTO> thisTeam = match.getTeams().stream()
                    .filter(team -> team.getTeamId().equals(teamId)).findFirst();
            Optional<MatchTeamDTO> otherTeam = match.getTeams().stream()
                    .filter(team -> !team.getTeamId().equals(teamId)).findFirst();

            if (thisTeam.isPresent() && otherTeam.isPresent()) {
                MatchTeamDTO team = thisTeam.get();
                MatchTeamDTO other = otherTeam.get();

                if (team.getScore() > other.getScore()) {
                    record.setWins(record.getWins() + 1);
                } else if (team.getScore() < other.getScore()) {
                    record.setLosses(record.getLosses() + 1);
                } else {
                    record.setTies(record.getTies() + 1);
                }
            }
        });

        double totalMatches = record.getWins() + record.getLosses() + record.getTies();

        if (totalMatches == 0) {
            record.setWinPercentage(new BigDecimal(0));
            return record;
        }
        double wins = record.getWins();
        record.setWinPercentage(new BigDecimal(wins / totalMatches).setScale(3, BigDecimal.ROUND_HALF_EVEN));
        return record;
    }
}
