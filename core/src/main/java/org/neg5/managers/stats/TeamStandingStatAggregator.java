package org.neg5.managers.stats;

import org.neg5.MatchTeamDTO;
import org.neg5.TeamRecordDTO;
import org.neg5.TeamStandingStatsDTO;
import org.neg5.TournamentMatchDTO;

import java.math.BigDecimal;
import java.util.stream.DoubleStream;

public class TeamStandingStatAggregator {

    private final String teamId;

    private int numMatches;

    private final DoubleStream.Builder pointsPerGameBuilder;
    private final DoubleStream.Builder pointsAgainstPerGameBuilder;

    private int tossupsHeard;

    private TeamRecordDTO teamRecord;

    private boolean aggregated;

    TeamStandingStatAggregator(String teamId) {
        this.teamId = teamId;

        pointsPerGameBuilder = DoubleStream.builder();
        pointsAgainstPerGameBuilder = DoubleStream.builder();

        teamRecord = new TeamRecordDTO();
    }

    void accept(TournamentMatchDTO match) {
        if (aggregated) {
            throw new IllegalStateException("Already called aggregate on team " + teamId);
        }
        TeamsWrapper teams = getTeams(match);
        pointsPerGameBuilder.accept(teams.thisTeam.getScore());
        pointsAgainstPerGameBuilder.accept(teams.otherTeam.getScore());

        tossupsHeard += match.getTossupsHeard();
        numMatches++;
        updateTeamRecord(teams);
    }

    TeamStandingStatsDTO aggregate() {

        finalizeRecord();
        TeamStandingStatsDTO stats = new TeamStandingStatsDTO();
        stats.setRecord(teamRecord);

        double ppg = pointsPerGameBuilder.build().average().orElse(0);
        stats.setPointsPerGame(new BigDecimal(ppg).setScale(2, BigDecimal.ROUND_HALF_UP));

        double papg = pointsAgainstPerGameBuilder.build().average().orElse(0);
        stats.setPointsAgainstPerGame(new BigDecimal(papg).setScale(2, BigDecimal.ROUND_HALF_UP));

        stats.setMarginOfVictory(stats.getPointsPerGame().subtract(stats.getPointsAgainstPerGame()));

        aggregated = true;

        return stats;
    }

    private void updateTeamRecord(TeamsWrapper wrapper) {
        if (wrapper.thisTeam.getScore() > wrapper.otherTeam.getScore()) {
            teamRecord.setWins(teamRecord.getWins() + 1);
        } else if (wrapper.thisTeam.getScore() < wrapper.otherTeam.getScore()) {
            teamRecord.setLosses(teamRecord.getLosses() + 1);
        } else {
            teamRecord.setTies(teamRecord.getTies() + 1);
        }
    }

    private void finalizeRecord() {
        if (numMatches == 0) {
            teamRecord.setWinPercentage(new BigDecimal(0));
        }
        double wins = teamRecord.getWins();
        teamRecord.setWinPercentage(new BigDecimal(wins / numMatches).setScale(3, BigDecimal.ROUND_HALF_EVEN));
    }

    private TeamsWrapper getTeams(TournamentMatchDTO match) {
        MatchTeamDTO thisTeam = match.getTeams().stream()
                .filter(team -> teamId.equals(team.getTeamId()))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Cannot find team " + teamId + " in match " + match.getId()));
        MatchTeamDTO otherTeam = match.getTeams().stream()
                .filter(team -> !teamId.equals(team.getTeamId()))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Cannot find non-team " + teamId + " in match " + match.getId()));

        return new TeamsWrapper(thisTeam, otherTeam);
    }

    private final class TeamsWrapper {

        MatchTeamDTO thisTeam;
        MatchTeamDTO otherTeam;

        private TeamsWrapper(MatchTeamDTO thisTeam, MatchTeamDTO otherTeam) {
            this.thisTeam = thisTeam;
            this.otherTeam = otherTeam;
        }
    }
}
