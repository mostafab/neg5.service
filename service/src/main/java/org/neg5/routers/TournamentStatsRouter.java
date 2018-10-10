package org.neg5.routers;

import com.google.inject.Inject;
import org.neg5.managers.stats.TeamStandingsStatsManager;

public class TournamentStatsRouter extends AbstractJsonRouter {

    @Inject private TeamStandingsStatsManager teamStandingsStatsManager;

    @Override
    public void registerRoutes() {
        get("/neg5-api/tournaments/:id/stats/team-standings", (request, response) ->
                teamStandingsStatsManager.calculate(request.params("id"), request.queryParams("phase")));
    }
}
