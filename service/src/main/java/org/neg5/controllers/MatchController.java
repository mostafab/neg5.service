package org.neg5.controllers;

import com.google.inject.Inject;
import org.neg5.TournamentMatchDTO;
import org.neg5.annotations.Controller;
import org.neg5.enums.TournamentAccessLevel;
import org.neg5.managers.TournamentMatchManager;
import org.neg5.security.TournamentAccessManager;
import org.neg5.util.RequestHelper;
import spark.Request;
import spark.Response;

@Controller("/neg5-api/matches")
public class MatchController extends AbstractJsonController {

    @Inject private TournamentMatchManager matchManager;
    @Inject private RequestHelper requestHelper;

    @Inject private TournamentAccessManager tournamentAccessManager;

    @Override
    public void registerRoutes() {
        get("/:id", this::getTournament);

        post("", (req, res) -> {
            TournamentMatchDTO tournamentMatchDTO = requestHelper.readFromRequest(req, TournamentMatchDTO.class);
            tournamentAccessManager.requireAtLeastLevel(tournamentMatchDTO.getTournamentId(), TournamentAccessLevel.COLLABORATOR);
            return matchManager.create(tournamentMatchDTO);
        });
    }

    private TournamentMatchDTO getTournament(Request request, Response response) {
        return matchManager.get(request.params("id"));
    }
}
