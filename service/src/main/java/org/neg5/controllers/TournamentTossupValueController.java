package org.neg5.controllers;

import com.google.inject.Inject;
import org.neg5.TournamentTossupValueDTO;
import org.neg5.core.GsonProvider;
import org.neg5.managers.TournamentTossupValueManager;
import spark.Spark;

public class TournamentTossupValueController extends AbstractJsonController {

    @Inject private GsonProvider gsonProvider;

    @Inject private TournamentTossupValueManager tournamentTossupValueManager;

    @Override
    public void registerRoutes() {
        Spark.post("/neg5-api/tossupValues", (req, res) -> {
            TournamentTossupValueDTO dto = gsonProvider.get().fromJson(req.body(), TournamentTossupValueDTO.class);
            return tournamentTossupValueManager.create(dto);
        }, obj -> gsonProvider.get().toJson(obj));
    }
}
