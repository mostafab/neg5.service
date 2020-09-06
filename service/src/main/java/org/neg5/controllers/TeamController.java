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
        put("/:id", (request, response) -> {
            validateHasAccessToEditTeam(request);
            TournamentTeamDTO team = requestHelper.readFromRequest(request, TournamentTeamDTO.class);
            team.setId(request.params("id"));
            return teamManager.update(team);
        });
        delete("/:id", (request, response) -> {
            validateHasAccessToEditTeam(request);
            teamManager.delete(request.params("id"));
            return null;
        });

        post("", this::createTeam);
    }

    private void validateHasAccessToEditTeam(Request request) {
        TournamentTeamDTO original = teamManager.get(request.params("id"));
        tournamentAccessManager.requireAccessLevel(
                original.getTournamentId(),
                TournamentAccessLevel.ADMIN
        );
    }

    private Object createTeam(Request request, Response response) {
        TournamentTeamDTO team = requestHelper.readFromRequest(request, TournamentTeamDTO.class);
        tournamentAccessManager.requireAccessLevel(team.getTournamentId(), TournamentAccessLevel.ADMIN);
        return teamManager.create(team);
    }
}
