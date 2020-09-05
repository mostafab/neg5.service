package org.neg5.controllers;

import com.google.inject.Inject;
import org.neg5.TournamentPlayerDTO;
import org.neg5.TournamentTeamDTO;
import org.neg5.enums.TournamentAccessLevel;
import org.neg5.managers.TournamentPlayerManager;
import org.neg5.security.TournamentAccessManager;
import org.neg5.util.RequestHelper;
import spark.Request;
import spark.Response;

public class PlayerController extends AbstractJsonController {

    @Inject private TournamentPlayerManager tournamentPlayerManager;
    @Inject private TournamentAccessManager tournamentAccessManager;
    @Inject private RequestHelper requestHelper;

    @Override
    protected String getBasePath() {
        return "/neg5-api/players";
    }

    @Override
    public void registerRoutes() {
        get("/:id", (req, res) -> tournamentPlayerManager.get(req.params("id")));
        put("/:id", (request, response) -> {
            TournamentPlayerDTO original = tournamentPlayerManager.get(request.params("id"));
            tournamentAccessManager.requireAccessLevel(
                    original.getTournamentId(),
                    TournamentAccessLevel.ADMIN
            );
            TournamentPlayerDTO player = requestHelper.readFromRequest(request, TournamentPlayerDTO.class);
            player.setId(request.params("id"));
            return tournamentPlayerManager.update(player);
        });

        post("", this::createPlayer);
    }

    private TournamentPlayerDTO createPlayer(Request request, Response response) {
        TournamentPlayerDTO playerDTO = requestHelper.readFromRequest(request, TournamentPlayerDTO.class);
        tournamentAccessManager.requireAccessLevel(playerDTO.getTournamentId(), TournamentAccessLevel.ADMIN);
        return tournamentPlayerManager.create(playerDTO);
    }
}
