package org.neg5.daos;

import org.neg5.data.TournamentPool;

public class TournamentDivisionDAO extends AbstractDAO<TournamentPool, String> {

    protected TournamentDivisionDAO() {
        super(TournamentPool.class);
    }
}
