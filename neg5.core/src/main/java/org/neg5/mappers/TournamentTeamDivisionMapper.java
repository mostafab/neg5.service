package org.neg5.mappers;

import org.neg5.TournamentTeamDivisionDTO;
import org.neg5.data.TournamentTeamDivision;

public class TournamentTeamDivisionMapper
        extends AbstractObjectMapper<TournamentTeamDivision, TournamentTeamDivisionDTO> {

    protected TournamentTeamDivisionMapper() {
        super(TournamentTeamDivision.class, TournamentTeamDivisionDTO.class);
    }
}
