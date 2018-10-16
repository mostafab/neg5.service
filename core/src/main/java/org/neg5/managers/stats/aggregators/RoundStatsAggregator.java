package org.neg5.managers.stats.aggregators;

import org.neg5.AnswersDTO;
import org.neg5.RoundStatDTO;
import org.neg5.TournamentMatchDTO;
import org.neg5.managers.stats.StatsUtilities;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class RoundStatsAggregator implements StatAggregator<RoundStatDTO> {

    private final Integer round;

    private int numResults;
    private int tossupsHeard;
    private double totalPoints;
    private int totalBouncebackPoints;
    private int totalOvertimeTossups;
    private int numMatches;

    private final Set<AnswersDTO> answers;

    public RoundStatsAggregator(Integer round) {
        this.round = round;

        answers = new HashSet<>();
    }

    @Override
    public void accept(TournamentMatchDTO match) {
        match.getTeams()
                .forEach(team -> {
                    totalPoints += team.getScore();
                    totalBouncebackPoints += team.getBouncebackPoints() == null
                            ? 0
                            : team.getBouncebackPoints();
                    totalOvertimeTossups += team.getOvertimeTossupsGotten() == null
                            ? 0
                            : team.getOvertimeTossupsGotten();
                });

        numResults += match.getTeams().size();
        tossupsHeard += match.getTossupsHeard();
        numMatches++;

        updateAnswers(match);
    }

    @Override
    public RoundStatDTO collect() {
        RoundStatDTO stat = new RoundStatDTO();
        stat.setRound(round);
        stat.setNumMatches(numMatches);
        stat.setAveragePointsPerGame(StatsUtilities.getPointsPerGame(totalPoints, numResults));
        stat.setTossupsHeard((double) tossupsHeard);
        stat.setTossupPoints(StatsUtilities.getTotalPoints(answers));
        stat.setAveragePointsPerBonus(StatsUtilities.calculatePointsPerBonus(answers,
                stat.getAveragePointsPerGame(), totalBouncebackPoints, totalOvertimeTossups, numResults));
        stat.setTossupAnswerCounts(StatsUtilities.aggregateAnswers(answers));
        return stat;
    }

    private void updateAnswers(TournamentMatchDTO match) {
        Set<AnswersDTO> matchAnswers = match.getTeams().stream()
                .flatMap(team -> team.getPlayers().stream())
                .flatMap(player -> player.getAnswers().stream())
                .map(playerAnswer -> {
                    AnswersDTO answers = new AnswersDTO();
                    answers.setValue(playerAnswer.getTossupValue());
                    answers.setTotal(playerAnswer.getNumberGotten());
                    answers.setAnswerType(playerAnswer.getAnswerType());
                    return answers;
                })
                .collect(Collectors.toSet());

        answers.addAll(matchAnswers);
    }
}
