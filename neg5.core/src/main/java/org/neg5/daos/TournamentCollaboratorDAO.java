package org.neg5.daos;

import org.neg5.data.TournamentCollaborator;
import org.neg5.data.embeddables.TournamentCollaboratorId;

public class TournamentCollaboratorDAO extends AbstractDAO<TournamentCollaborator, TournamentCollaboratorId> {

    protected TournamentCollaboratorDAO() {
        super(TournamentCollaborator.class);
    }

    @Override
    protected String getTournamentIdAttributePath() {
        return "id.tournament.id";
    }

    public TournamentCollaborator getCollaboratorByUsernameAndTournament(String username, String tournamentId) {
        return getEntityManager()
                .createQuery("SELECT c from TournamentCollaborator c where c.id.user.id = :username AND "
                        + "c.id.tournament.id = :tournamentId", TournamentCollaborator.class)
                .setParameter("username", username)
                .setParameter("tournamentId", tournamentId)
                .getSingleResult();
    }
}
