package org.neg5.mappers;

import org.neg5.TournamentCollaboratorDTO;
import org.neg5.data.TournamentCollaborator;

public class TournamentCollaboratorMapper extends AbstractObjectMapper<TournamentCollaborator, TournamentCollaboratorDTO> {

    protected TournamentCollaboratorMapper() {
        super(TournamentCollaborator.class, TournamentCollaboratorDTO.class);
    }
}
