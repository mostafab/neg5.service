package org.neg5.managers.stats;

import java.math.BigDecimal;

final class StatsUtilities {

    static BigDecimal calculatePointsPerTossupsHeard(int tossupsHeard,
                                                     int numMatches,
                                                     BigDecimal pointsPerGame) {
        if (tossupsHeard == 0) {
            return new BigDecimal(0);
        }
        return pointsPerGame
                .multiply(new BigDecimal(numMatches))
                .divide(new BigDecimal(tossupsHeard), BigDecimal.ROUND_UP)
                .setScale(2, BigDecimal.ROUND_UP);
    }
}
