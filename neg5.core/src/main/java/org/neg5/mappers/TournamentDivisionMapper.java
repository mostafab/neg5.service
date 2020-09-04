package org.neg5.mappers;

import com.google.inject.Singleton;
import org.neg5.TournamentPoolDTO;
import org.neg5.data.TournamentPool;

@Singleton
public class TournamentDivisionMapper extends AbstractObjectMapper<TournamentPool, TournamentPoolDTO> {

    protected TournamentDivisionMapper() {
        super(TournamentPool.class, TournamentPoolDTO.class);
    }
}
