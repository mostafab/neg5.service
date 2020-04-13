package org.neg5.mappers;

import com.google.inject.Singleton;
import org.neg5.TournamentCollaboratorDTO;
import org.neg5.data.TournamentCollaborator;

@Singleton
public class TournamentCollaboratorMapper extends AbstractObjectMapper<TournamentCollaborator, TournamentCollaboratorDTO> {

    protected TournamentCollaboratorMapper() {
        super(TournamentCollaborator.class, TournamentCollaboratorDTO.class);
    }
}
