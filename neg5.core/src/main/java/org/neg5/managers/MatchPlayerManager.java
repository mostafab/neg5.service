package org.neg5.managers;

import com.google.inject.Inject;
import org.neg5.MatchPlayerDTO;
import org.neg5.daos.MatchPlayerDAO;
import org.neg5.data.MatchPlayer;
import org.neg5.data.embeddables.MatchPlayerId;
import org.neg5.mappers.MatchPlayerMapper;

public class MatchPlayerManager extends AbstractDTOManager<MatchPlayer, MatchPlayerDTO, MatchPlayerId> {

    @Inject private MatchPlayerDAO matchPlayerDAO;
    @Inject private MatchPlayerMapper matchPlayerMapper;

    @Inject
    public MatchPlayerManager(MatchPlayerDAO matchPlayerDAO,
                              MatchPlayerMapper matchPlayerMapper) {
        this.matchPlayerDAO = matchPlayerDAO;
        this.matchPlayerMapper = matchPlayerMapper;
    }

    @Override
    protected MatchPlayerMapper getMapper() {
        return matchPlayerMapper;
    }

    @Override
    protected MatchPlayerDAO getRwDAO() {
        return matchPlayerDAO;
    }
}
