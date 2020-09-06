package org.neg5.managers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.neg5.TournamentTeamDivisionDTO;
import org.neg5.daos.TournamentTeamDivisionDAO;
import org.neg5.data.TournamentTeamDivision;
import org.neg5.data.embeddables.TournamentTeamDivisionId;
import org.neg5.mappers.TournamentTeamDivisionMapper;

@Singleton
public class TournamentTeamDivisionManager
        extends AbstractDTOManager<TournamentTeamDivision, TournamentTeamDivisionDTO, TournamentTeamDivisionId> {

    private final TournamentTeamDivisionMapper mapper;
    private final TournamentTeamDivisionDAO dao;

    @Inject
    public TournamentTeamDivisionManager(TournamentTeamDivisionMapper mapper,
                                         TournamentTeamDivisionDAO dao) {
        this.mapper = mapper;
        this.dao = dao;
    }

    @Override
    protected TournamentTeamDivisionDAO getRwDAO() {
        return dao;
    }

    @Override
    protected TournamentTeamDivisionMapper getMapper() {
        return mapper;
    }
}
