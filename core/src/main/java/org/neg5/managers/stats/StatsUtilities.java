package org.neg5.managers.stats;

import org.neg5.AnswersDTO;
import org.neg5.enums.TossupAnswerType;

import java.math.BigDecimal;
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
}
