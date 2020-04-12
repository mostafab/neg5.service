package org.neg5.daos;

import org.neg5.data.MatchPlayerAnswer;
import org.neg5.data.embeddables.MatchPlayerAnswerId;

public class MatchPlayerAnswerDAO extends AbstractDAO<MatchPlayerAnswer, MatchPlayerAnswerId> {

    protected MatchPlayerAnswerDAO() {
        super(MatchPlayerAnswer.class);
    }
}
