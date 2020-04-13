package org.neg5.daos;

import org.neg5.data.MatchPlayer;
import org.neg5.data.embeddables.MatchPlayerId;

public class MatchPlayerDAO extends AbstractDAO<MatchPlayer, MatchPlayerId> {

    protected MatchPlayerDAO() {
        super(MatchPlayer.class);
    }
}
