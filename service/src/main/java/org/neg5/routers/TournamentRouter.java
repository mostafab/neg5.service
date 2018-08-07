package org.neg5.routers;

import com.google.inject.Inject;
import org.neg5.managers.TournamentManager;

public class TournamentRouter extends AbstractJsonRouter {

    @Inject private TournamentManager tournamentManager;

    @Override
    public void registerRoutes() {
        get("/neg5-api/tournaments/:id", (request, response) -> tournamentManager.get(request.params("id")));
    }
}
