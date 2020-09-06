package org.neg5.data;

import org.neg5.data.embeddables.TournamentTeamDivisionId;

import javax.persistence.EmbeddedId;

public class TournamentTeamDivision extends AbstractDataObject<TournamentTeamDivision>
    implements CompositeIdObject<TournamentTeamDivisionId> {

    private TournamentTeamDivisionId id;

    @Override
    @EmbeddedId
    public TournamentTeamDivisionId getId() {
        return id;
    }

    @Override
    public void setId(TournamentTeamDivisionId id) {
        this.id = id;
    }
}
