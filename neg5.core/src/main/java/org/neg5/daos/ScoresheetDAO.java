package org.neg5.daos;

import com.google.inject.Singleton;
import org.neg5.data.Scoresheet;

@Singleton
public class ScoresheetDAO extends AbstractDAO<Scoresheet, Long> {

    protected ScoresheetDAO() {
        super(Scoresheet.class);
    }
}
