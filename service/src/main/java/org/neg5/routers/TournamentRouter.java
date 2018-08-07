package org.neg5.routers;

import com.google.inject.Inject;
import org.neg5.managers.TournamentManager;

import org.neg5.transformers.JsonTransformerProvider;

import static spark.Spark.get;

public class TournamentRouter implements Router {

    @Inject private TournamentManager tournamentManager;
    @Inject private JsonTransformerProvider jsonTransformerProvider;

    @Override
    public void registerRoutes() {
        get("/neg5-api/tournaments/:id", (request, response) -> {
            return tournamentManager.get(request.params("id"));
        }, jsonTransformerProvider.get());
    }
}
