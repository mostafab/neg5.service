package org.neg5.controllers;

import com.google.inject.Inject;
import org.neg5.TournamentTeamDTO;
import org.neg5.enums.TournamentAccessLevel;
import org.neg5.managers.TournamentTeamManager;
import org.neg5.security.TournamentAccessManager;
import org.neg5.util.RequestHelper;
import spark.Request;
import spark.Response;

public class TeamController extends AbstractJsonController {

    @Inject private TournamentTeamManager teamManager;
    @Inject private TournamentAccessManager tournamentAccessManager;
    @Inject private RequestHelper requestHelper;

    @Override
    protected String getBasePath() {
        return "/neg5-api/teams";
    }

    @Override
    public void registerRoutes() {
        get("/:id", (request, response) -> teamManager.get(request.params("id")));
        post("", this::createTeam);
    }

    private Object createTeam(Request request, Response response) {
        TournamentTeamDTO team = requestHelper.readFromRequest(request, TournamentTeamDTO.class);
        tournamentAccessManager.requireAccessLevel(team.getTournamentId(), TournamentAccessLevel.ADMIN);
        return teamManager.create(team);
    }
}
