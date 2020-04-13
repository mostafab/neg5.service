package org.neg5.controllers;

import com.google.inject.Inject;
import org.neg5.TournamentTossupValueDTO;
import org.neg5.annotations.Controller;
import org.neg5.core.GsonProvider;
import org.neg5.enums.TournamentAccessLevel;
import org.neg5.managers.TournamentTossupValueManager;
import org.neg5.security.TournamentAccessManager;
import org.neg5.util.RequestHelper;
import spark.Spark;

@Controller("/neg5-api/tossup-values")
public class TournamentTossupValueController extends AbstractJsonController {

    @Inject private TournamentTossupValueManager tournamentTossupValueManager;
    @Inject private TournamentAccessManager tournamentAccessManager;
    @Inject private RequestHelper requestHelper;

    @Override
    public void registerRoutes() {
        post("", (req, res) -> {
            TournamentTossupValueDTO dto = requestHelper.readFromRequest(req, TournamentTossupValueDTO.class);
            tournamentAccessManager.requireAccessLevel(dto.getTournamentId(), TournamentAccessLevel.OWNER);
            return tournamentTossupValueManager.create(dto);
        });
    }
}
