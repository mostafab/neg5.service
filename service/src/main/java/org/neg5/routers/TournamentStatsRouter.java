package org.neg5.routers;

import com.google.inject.Inject;
import org.neg5.managers.stats.IndividualStandingsStatsManager;
import org.neg5.managers.stats.TeamStandingsStatsManager;

public class TournamentStatsRouter extends AbstractJsonRouter {

    @Inject private TeamStandingsStatsManager teamStandingsStatsManager;
    @Inject private IndividualStandingsStatsManager individualStandingsStatsManager;

    @Override
    public void registerRoutes() {
        get("/neg5-api/tournaments/:id/stats/team-standings", (request, response) ->
                teamStandingsStatsManager.calculateTeamStandings(request.params("id"), request.queryParams("phase")));
        get("/neg5-api/tournaments/:id/stats/individual-standings", (request, response) ->
                individualStandingsStatsManager.calculateIndividualStandings(request.params("id"), request.queryParams("phase")));
        get("/neg5-api/tournaments/:id/stats/team-full-standings", (request, response) ->
                teamStandingsStatsManager.calculateFullTeamStandings(request.params("id"), request.queryParams("phase")));
    }
}
