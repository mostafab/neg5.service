package org.neg5.managers.stats;

import java.math.BigDecimal;

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
}
