package org.neg5.managers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.neg5.TournamentCollaboratorDTO;
import org.neg5.daos.TournamentCollaboratorDAO;
import org.neg5.data.TournamentCollaborator;
import org.neg5.data.embeddables.TournamentCollaboratorId;
import org.neg5.mappers.TournamentCollaboratorMapper;

import javax.persistence.NoResultException;
import java.util.Optional;

@Singleton
public class TournamentCollaboratorManager
        extends AbstractDTOManager<TournamentCollaborator, TournamentCollaboratorDTO, TournamentCollaboratorId> {

    @Inject private TournamentCollaboratorMapper tournamentCollaboratorMapper;
    @Inject private TournamentCollaboratorDAO tournamentCollaboratorDAO;

    @Override
    protected TournamentCollaboratorDAO getRwDAO() {
        return tournamentCollaboratorDAO;
    }

    @Override
    protected TournamentCollaboratorMapper getMapper() {
        return tournamentCollaboratorMapper;
    }

    public TournamentCollaboratorDTO addOrUpdateCollaborator(TournamentCollaboratorDTO collaborator) {
        Optional<TournamentCollaboratorDTO> existing =
                getByTournamentAndUsername(collaborator.getTournamentId(),collaborator.getUserId());

        if (existing.isPresent()) {
            return update(collaborator);
        }
        return create(collaborator);
    }

    public Optional<TournamentCollaboratorDTO> getByTournamentAndUsername(String tournamentId,
                                                                          String username) {
        try {
            return Optional.of(tournamentCollaboratorMapper
                    .toDTO(getRwDAO().getCollaboratorByUsernameAndTournament(tournamentId, username)));
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
