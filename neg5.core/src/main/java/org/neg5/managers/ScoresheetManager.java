package org.neg5.managers;

import com.google.inject.Inject;
import org.neg5.ScoresheetDTO;
import org.neg5.daos.ScoresheetDAO;
import org.neg5.data.Scoresheet;
import org.neg5.mappers.ScoresheetMapper;

public class ScoresheetManager extends AbstractDTOManager<Scoresheet, ScoresheetDTO, Long> {

    @Inject private ScoresheetDAO scoresheetDAO;
    @Inject private ScoresheetMapper scoresheetMapper;

    @Override
    protected ScoresheetMapper getMapper() {
        return scoresheetMapper;
    }

    @Override
    protected ScoresheetDAO getRwDAO() {
        return scoresheetDAO;
    }
}
