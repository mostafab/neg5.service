package org.neg5.routers;

import com.google.inject.Inject;
import org.neg5.managers.TournamentManager;
import org.neg5.managers.TournamentTeamManager;

public class TournamentRouter extends AbstractJsonRouter {

    @Inject private TournamentManager tournamentManager;
    @Inject private TournamentTeamManager tournamentTeamManager;

    @Override
    public void registerRoutes() {
        get("/neg5-api/tournaments/:id", (request, response) -> tournamentManager.get(request.params("id")));
        get("/neg5-api/tournaments/:id/teams", (request, response) -> tournamentTeamManager.findAllByTournamentId(request.params("id")));
    }
}
