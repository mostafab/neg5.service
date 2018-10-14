package org.neg5.managers.stats;

import org.neg5.AnswersDTO;
import org.neg5.enums.TossupAnswerType;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Common utility stats functions
 */
public final class StatsUtilities {

    private static final int ROUNDING_SCALE = 2;

    /**
     * Calculate points per tossup heard
     * @param tossupsHeard tossups heard
     * @param numMatches number of matches
     * @param pointsPerGame points per game
     * @return a BigDecimal representation of points per tossup heard, rounded to 2 decimal places.
     */
    public static BigDecimal calculatePointsPerTossupsHeard(int tossupsHeard,
                                                     int numMatches,
                                                     BigDecimal pointsPerGame) {
        if (tossupsHeard == 0) {
            return new BigDecimal(0);
        }
        return pointsPerGame
                .multiply(new BigDecimal(numMatches))
                .divide(new BigDecimal(tossupsHeard), BigDecimal.ROUND_UP)
                .setScale(ROUNDING_SCALE, BigDecimal.ROUND_UP);
    }

    /**
     * Calculate points per bonus
     * @param answers set of answers
     * @param pointsPerGame points per game
     * @param numMatches number of matches
     * @return points per bonus
     */
    public static BigDecimal calculatePointsPerBonus(Set<AnswersDTO> answers,
                                                     BigDecimal pointsPerGame,
                                                     int numMatches) {
        BigDecimal totalGets = new BigDecimal(
                answers.stream()
                        .filter(answer -> !TossupAnswerType.NEG.equals(answer.getAnswerType()))
                        .mapToInt(AnswersDTO::getTotal)
                        .sum()
        );
        if (totalGets.equals(new BigDecimal(0))) {
            return new BigDecimal(0);
        }
        BigDecimal totalPoints = pointsPerGame.multiply(new BigDecimal(numMatches));

        BigDecimal pointsFromTossups = new BigDecimal(
                answers.stream()
                        .mapToDouble(answer -> answer.getTotal() * answer.getValue())
                        .sum()
        );
        return totalPoints.subtract(pointsFromTossups).divide(totalGets, BigDecimal.ROUND_UP)
                .setScale(BigDecimal.ROUND_HALF_UP, BigDecimal.ROUND_UP);
    }

    /**
     * Calculate tossup powers to negs ratio
     * @param answers list of answers to consider
     * @return ratio, or 0 if no negs or no powers
     */
    public static BigDecimal calculatePowerToNegRatio(Set<AnswersDTO> answers) {
        Map<TossupAnswerType, Integer> answerTypeCounts = buildAnswerTypeCountsMap(answers);
        int negs = answerTypeCounts.getOrDefault(TossupAnswerType.NEG, 0);
        int powers = answerTypeCounts.getOrDefault(TossupAnswerType.POWER, 0);
        if (negs == 0 || powers == 0) {
            return new BigDecimal(0);
        }
        return new BigDecimal(powers).divide(new BigDecimal(negs), ROUNDING_SCALE,  BigDecimal.ROUND_HALF_EVEN);
    }

    /**
     * Calculate get to neg ratio
     * @param answers list of answers to consider
     * @return ratio, or 0 if no negs or gets.
     */
    public static BigDecimal calculateGetsToNegRatio(Set<AnswersDTO> answers) {
        Map<TossupAnswerType, Integer> answerTypeCounts = buildAnswerTypeCountsMap(answers);
        int negs = answerTypeCounts.getOrDefault(TossupAnswerType.NEG, 0);
        int gets = answerTypeCounts.getOrDefault(TossupAnswerType.POWER, 0)
                + answerTypeCounts.getOrDefault(TossupAnswerType.BASE, 0);
        if (negs == 0 || gets == 0) {
            return new BigDecimal(0);
        }
        return new BigDecimal(gets).divide(new BigDecimal(negs), ROUNDING_SCALE, BigDecimal.ROUND_HALF_EVEN);
    }

    private static Map<TossupAnswerType, Integer> buildAnswerTypeCountsMap(Set<AnswersDTO> answers) {
        Map<TossupAnswerType, Integer> counts = new HashMap<>();
        answers.stream()
                .filter(answer -> answer.getAnswerType() != null)
                .forEach(answer -> {
                    counts.computeIfPresent(answer.getAnswerType(), (answerType, count) -> count + answer.getTotal());
                    counts.putIfAbsent(answer.getAnswerType(), answer.getTotal());
                });
        return counts;
    }
}
