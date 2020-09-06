package org.neg5.mappers;

import com.google.inject.Singleton;
import org.neg5.TournamentTeamPoolDTO;
import org.neg5.data.TournamentTeamPool;

@Singleton
public class TournamentTeamPoolMapper
        extends AbstractObjectMapper<TournamentTeamPool, TournamentTeamPoolDTO> {

    protected TournamentTeamPoolMapper() {
        super(TournamentTeamPool.class, TournamentTeamPoolDTO.class);
    }
}
