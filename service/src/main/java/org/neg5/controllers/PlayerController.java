package org.neg5.controllers;

import com.google.inject.Inject;
import org.neg5.annotations.Controller;
import org.neg5.managers.TournamentPlayerManager;

@Controller("/neg5-api/players")
public class PlayerController extends AbstractJsonController {

    @Inject private TournamentPlayerManager tournamentPlayerManager;

    @Override
    public void registerRoutes() {
        get("/:id", (req, res) -> tournamentPlayerManager.get(req.params("id")));
    }
}
