package org.neg5.mappers;

import com.google.inject.Singleton;
import org.neg5.TournamentPlayerDTO;
import org.neg5.data.TournamentPlayer;

@Singleton
public class TournamentPlayerMapper extends AbstractObjectMapper<TournamentPlayer, TournamentPlayerDTO> {

    protected TournamentPlayerMapper() {
        super(TournamentPlayer.class, TournamentPlayerDTO.class);
    }
}