package org.neg5.managers.stats.aggregators;

import org.neg5.AnswersDTO;
import org.neg5.MatchTeamDTO;
import org.neg5.TeamMatchStatsDTO;
import org.neg5.TournamentMatchDTO;
import org.neg5.managers.stats.StatsUtilities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TeamMatchesStatsAggregator implements StatAggregator<List<TeamMatchStatsDTO>> {

    private final String teamId;

    private final List<TeamMatchStatsDTO> matches;

    public TeamMatchesStatsAggregator(String teamId) {
        this.teamId = teamId;

        matches = new ArrayList<>();
    }

    @Override
    public void accept(TournamentMatchDTO match) {
        matches.add(calculateTeamMatchStats(match));
    }

    @Override
    public List<TeamMatchStatsDTO> collect() {
        return matches;
    }

    private TeamMatchStatsDTO calculateTeamMatchStats(TournamentMatchDTO match) {
        MatchUtil.TeamsWrapper teams = MatchUtil.getTeams(teamId, match);

        TeamMatchStatsDTO stats = new TeamMatchStatsDTO();
        stats.setOpponentTeamId(teams.getOtherTeam().getTeamId());
        stats.setOpponentPoints(teams.getOtherTeam().getScore().doubleValue());

        MatchTeamDTO thisTeam = teams.getThisTeam();
        stats.setPoints(thisTeam.getScore().doubleValue());

        Set<AnswersDTO> answers = aggregateAnswers(StatsUtilities.getAnswers(thisTeam));
        stats.setTossupAnswerCounts(answers);
        stats.setPowersToNegRatio(StatsUtilities.calculatePowerToNegRatio(answers));
        stats.setGetsToNegRatio(StatsUtilities.calculateGetsToNegRatio(answers));

        stats.setTossupsHeard(match.getTossupsHeard() == null ? 0 : match.getTossupsHeard().doubleValue());
        stats.setPointsPerTossupHeard(StatsUtilities.calculatePointsPerTossupsHeard(stats.getTossupsHeard().intValue(),
                1, new BigDecimal(stats.getPoints())));

        stats.setBonusPoints(StatsUtilities.getBonusPoints(stats.getPoints(), answers));

        return stats;
    }

    private Set<AnswersDTO> aggregateAnswers(Set<AnswersDTO> answers) {
        Map<Integer, AnswersDTO> tossupValueCounts = new HashMap<>();
        answers.forEach(answer -> {
            tossupValueCounts.computeIfPresent(answer.getValue(), (value, dto) -> {
                dto.setTotal(dto.getTotal() + answer.getTotal());
                return dto;
            });
            tossupValueCounts.computeIfAbsent(answer.getValue(), value -> {
               AnswersDTO dto = new AnswersDTO();
               dto.setValue(value);
               dto.setAnswerType(answer.getAnswerType());
               dto.setTotal(answer.getTotal());
               return dto;
            });
        });
        return new HashSet<>(tossupValueCounts.values());
    }
}
