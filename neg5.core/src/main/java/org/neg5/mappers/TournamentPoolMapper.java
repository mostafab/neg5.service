package org.neg5.mappers;

import com.google.inject.Singleton;
import org.neg5.TournamentPoolDTO;
import org.neg5.data.TournamentPool;

@Singleton
public class TournamentPoolMapper extends AbstractObjectMapper<TournamentPool, TournamentPoolDTO> {

    protected TournamentPoolMapper() {
        super(TournamentPool.class, TournamentPoolDTO.class);
    }
}
