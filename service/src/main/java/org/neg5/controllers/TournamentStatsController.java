package org.neg5.controllers;

import com.google.inject.Inject;
import org.neg5.annotations.Controller;
import org.neg5.managers.stats.IndividualStandingsStatsManager;
import org.neg5.managers.stats.RoundReportStatsManager;
import org.neg5.managers.stats.TeamStandingsStatsManager;

@Controller("/neg5-api/tournaments/:id/stats")
public class TournamentStatsController extends AbstractJsonController {

    @Inject private TeamStandingsStatsManager teamStandingsStatsManager;
    @Inject private IndividualStandingsStatsManager individualStandingsStatsManager;
    @Inject private RoundReportStatsManager roundReportStatsManager;

    @Override
    public void registerRoutes() {
        get("/team-standings", (request, response) ->
                teamStandingsStatsManager.calculateTeamStandings(request.params("id"), request.queryParams("phase")));
        get("/individual-standings", (request, response) ->
                individualStandingsStatsManager.calculateIndividualStandings(request.params("id"), request.queryParams("phase")));
        get("/team-full-standings", (request, response) ->
                teamStandingsStatsManager.calculateFullTeamStandings(request.params("id"), request.queryParams("phase")));
        get("/individual-full-standings", (request, response) ->
                individualStandingsStatsManager.getFullIndividualStats(request.params("id"), request.queryParams("phase")));
        get("/round-report", (request, response) ->
                roundReportStatsManager.calculateRoundReportStats(request.params("id"), request.queryParams("phase")));
    }
}
