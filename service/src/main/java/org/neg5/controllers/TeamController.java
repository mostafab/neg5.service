package org.neg5.controllers;

import com.google.inject.Inject;
import org.neg5.managers.TournamentTeamManager;
import org.neg5.annotations.Controller;

@Controller("/neg5-api/teams")
public class TeamController extends AbstractJsonController implements org.neg5.controllers.Controller {

    @Inject private TournamentTeamManager teamManager;

    @Override
    public void registerRoutes() {
        get("/:id", (request, response) -> teamManager.get(request.params("id")));
    }
}
