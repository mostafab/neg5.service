package org.neg5.daos;

import org.neg5.data.MatchTeam;
import org.neg5.data.embeddables.MatchTeamId;

public class MatchTeamDAO extends AbstractDAO<MatchTeam, MatchTeamId> {

    protected MatchTeamDAO() {
        super(MatchTeam.class);
    }
}
