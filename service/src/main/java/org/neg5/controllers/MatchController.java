package org.neg5.controllers;

import com.google.inject.Inject;
import org.neg5.annotations.Controller;
import org.neg5.managers.TournamentMatchManager;

@Controller("/neg5-api/matches")
public class MatchController extends AbstractJsonController {

    @Inject private TournamentMatchManager matchManager;

    @Override
    public void registerRoutes() {
        get("/:id", (req, res) -> matchManager.get(req.params("id")));
    }
}
