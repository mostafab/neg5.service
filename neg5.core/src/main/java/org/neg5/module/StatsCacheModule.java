package org.neg5.module;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import org.neg5.BaseAggregateStatsDTO;
import org.neg5.FullIndividualMatchesStatsDTO;
import org.neg5.FullTeamsMatchesStatsDTO;
import org.neg5.IndividualStandingsStatsDTO;
import org.neg5.RoundsReportStatsDTO;
import org.neg5.TeamStandingsStatsDTO;
import org.neg5.cache.TournamentStatsCache;
import org.neg5.cache.TournamentStatsCacheImpl;

/**
 * Guice module for providing stat caches
 */
public class StatsCacheModule extends AbstractModule {

    private static final int MAX_SIZE = 50;
    private static final int MINUTES_TO_KEEP = 5;

     @Override
     protected void configure() {
         Multibinder<TournamentStatsCache> statsBinder = Multibinder.newSetBinder(binder(), TournamentStatsCache.class);
         statsBinder.addBinding().toInstance(getCache(TeamStandingsStatsDTO.class));
         statsBinder.addBinding().toInstance(getCache(FullTeamsMatchesStatsDTO.class));
         statsBinder.addBinding().toInstance(getCache(IndividualStandingsStatsDTO.class));
         statsBinder.addBinding().toInstance(getCache(FullIndividualMatchesStatsDTO.class));
         statsBinder.addBinding().toInstance(getCache(RoundsReportStatsDTO.class));
     }

     private <T extends BaseAggregateStatsDTO> TournamentStatsCache<T> getCache(Class<T> statsClazz) {
         return new TournamentStatsCacheImpl<>(MAX_SIZE, MINUTES_TO_KEEP, statsClazz);
     }
}
