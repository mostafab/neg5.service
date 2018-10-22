package org.neg5.controllers;

import com.google.inject.Inject;
import org.neg5.annotations.Controller;
import org.neg5.managers.TournamentManager;
import org.neg5.managers.TournamentMatchManager;
import org.neg5.managers.TournamentPhaseManager;
import org.neg5.managers.TournamentPlayerManager;
import org.neg5.managers.TournamentTeamManager;
import org.neg5.managers.TournamentTossupValueManager;

@Controller("/neg5-api/tournaments")
public class TournamentController extends AbstractJsonController {

    @Inject private TournamentManager tournamentManager;
    @Inject private TournamentTeamManager tournamentTeamManager;
    @Inject private TournamentPlayerManager tournamentPlayerManager;
    @Inject private TournamentMatchManager tournamentMatchManager;
    @Inject private TournamentPhaseManager tournamentPhaseManager;
    @Inject private TournamentTossupValueManager tournamentTossupValueManager;

    @Override
    public void registerRoutes() {
        get("/:id", (request, response)
                -> tournamentManager.get(request.params("id")));
        get("/:id/teams", (request, response)
                -> tournamentTeamManager.findAllByTournamentId(request.params("id")));
        get("/:id/players", (request, response)
                -> tournamentPlayerManager.findAllByTournamentId(request.params("id")));
        get("/:id/matches", (request, response)
                -> tournamentMatchManager.findAllByTournamentId(request.params("id")));
        get("/:id/phases", (request, response)
                -> tournamentPhaseManager.findAllByTournamentId(request.params("id")));
        get("/:id/tossupValues", (request, response)
                -> tournamentTossupValueManager.findAllByTournamentId(request.params("id")));
    }
}
