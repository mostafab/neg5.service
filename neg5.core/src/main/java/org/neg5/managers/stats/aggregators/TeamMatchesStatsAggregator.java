package org.neg5.managers.stats.aggregators;

import org.neg5.AnswersDTO;
import org.neg5.MatchTeamDTO;
import org.neg5.TeamMatchStatsDTO;
import org.neg5.TournamentMatchDTO;
import org.neg5.enums.MatchResult;
import org.neg5.enums.TossupAnswerType;
import org.neg5.managers.stats.StatsUtilities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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
        return new ArrayList<>(matches);
    }

    private TeamMatchStatsDTO calculateTeamMatchStats(TournamentMatchDTO match) {
        MatchUtil.TeamsWrapper teams = MatchUtil.getTeams(teamId, match);

        TeamMatchStatsDTO stats = new TeamMatchStatsDTO();
        stats.setRound(match.getRound() == null ? null : match.getRound().intValue());
        stats.setOpponentTeamId(teams.getOtherTeam().getTeamId());
        stats.setOpponentPoints(teams.getOtherTeam().getScore().doubleValue());
        stats.setResult(getResult(teams));

        MatchTeamDTO thisTeam = teams.getThisTeam();
        stats.setPoints(thisTeam.getScore().doubleValue());

        Set<AnswersDTO> answers = StatsUtilities.aggregateAnswers(StatsUtilities.getAnswers(thisTeam));
        stats.setTossupAnswerCounts(answers);
        stats.setPowersToNegRatio(StatsUtilities.calculatePowerToNegRatio(answers));
        stats.setGetsToNegRatio(StatsUtilities.calculateGetsToNegRatio(answers));

        stats.setTossupsHeard(match.getTossupsHeard() == null ? 0 : match.getTossupsHeard().doubleValue());
        stats.setPointsPerTossupHeard(StatsUtilities.calculatePointsPerTossupsHeard(stats.getTossupsHeard().intValue(),
                1, new BigDecimal(stats.getPoints())));

        stats.setBouncebackPoints(thisTeam.getBouncebackPoints() == null
                ? null
                : thisTeam.getBouncebackPoints().doubleValue()
        );
        stats.setBonusPoints(StatsUtilities.getBonusPoints(stats.getPoints(), stats.getBouncebackPoints(), answers));
        stats.setBonusesHeard(getBonusesHeard(answers, thisTeam.getOvertimeTossupsGotten()));
        stats.setPointsPerBonus(getPointsPerBonus(thisTeam, stats.getPoints(), answers));

        return stats;
    }

    private Integer getBonusesHeard(Set<AnswersDTO> answers, Integer overtimeTossups) {
        return answers.stream()
                .filter(answer -> TossupAnswerType.NEG != answer.getAnswerType())
                .mapToInt(AnswersDTO::getTotal)
                .sum() - (overtimeTossups == null ? 0 : overtimeTossups);
    }

    private BigDecimal getPointsPerBonus(MatchTeamDTO thisTeam,
                                         Double points,
                                         Set<AnswersDTO> answers) {
        return StatsUtilities.calculatePointsPerBonus(
                answers,
                new BigDecimal(points),
                thisTeam.getBouncebackPoints() == null ? 0 : thisTeam.getBouncebackPoints(),
                thisTeam.getOvertimeTossupsGotten() == null ? 0 : thisTeam.getOvertimeTossupsGotten(),
                1
        );
    }

    private MatchResult getResult(MatchUtil.TeamsWrapper teams) {
        MatchTeamDTO thisTeam = teams.getThisTeam();
        MatchTeamDTO otherTeam = teams.getOtherTeam();

        if (thisTeam.getScore() == null || otherTeam.getScore() == null) {
            return null;
        }
        Integer thisScore = thisTeam.getScore();
        Integer otherScore = otherTeam.getScore();
        if (thisScore > otherScore) {
            return MatchResult.WIN;
        }
        if (thisScore < otherScore) {
            return MatchResult.LOSS;
        }
        return MatchResult.TIE;
    }
}
