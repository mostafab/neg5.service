package org.neg5.controllers;

import com.google.inject.Inject;
import org.neg5.annotations.Controller;
import org.neg5.managers.stats.IndividualStandingsStatsManager;
import org.neg5.managers.stats.RoundReportStatsManager;
import org.neg5.managers.stats.StatsCacheManager;
import org.neg5.managers.stats.TeamStandingsStatsManager;

@Controller("/neg5-api/tournaments/:id/stats")
public class TournamentStatsController extends AbstractJsonController {

    @Inject private TeamStandingsStatsManager teamStandingsStatsManager;
    @Inject private IndividualStandingsStatsManager individualStandingsStatsManager;
    @Inject private RoundReportStatsManager roundReportStatsManager;

    @Inject private StatsCacheManager statsCacheManager;

    @Override
    public void registerRoutes() {
        get("/team-standings", (request, response) ->
                teamStandingsStatsManager.getCachedTeamStandings(request.params("id"), request.queryParams("phase")));
        get("/individual-standings", (request, response) ->
                individualStandingsStatsManager.getCachedIndividualStandings(request.params("id"), request.queryParams("phase")));
        get("/team-full-standings", (request, response) ->
                teamStandingsStatsManager.getCachedFullTeamStandings(request.params("id"), request.queryParams("phase")));
        get("/individual-full-standings", (request, response) ->
                individualStandingsStatsManager.getCachedFullIndividualStats(request.params("id"), request.queryParams("phase")));
        get("/round-report", (request, response) ->
                roundReportStatsManager.getCachedStats(request.params("id"), request.queryParams("phase")));

        post("/invalidate",
                (request, response) -> statsCacheManager.invalidateStats(request.params("id")));
    }
}
