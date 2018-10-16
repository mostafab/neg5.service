package org.neg5.managers.stats.aggregators;

import org.neg5.MatchTeamDTO;
import org.neg5.TournamentMatchDTO;

/**
 * Utility class for matches
 */
final class MatchUtil {

    private MatchUtil() {}

    static TeamsWrapper getTeams(String teamId, TournamentMatchDTO match) {
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

    final static class TeamsWrapper {

        private final MatchTeamDTO thisTeam;
        private final MatchTeamDTO otherTeam;

        TeamsWrapper(MatchTeamDTO thisTeam, MatchTeamDTO otherTeam) {
            this.thisTeam = thisTeam;
            this.otherTeam = otherTeam;
        }

        MatchTeamDTO getThisTeam() {
            return thisTeam;
        }

        MatchTeamDTO getOtherTeam() {
            return otherTeam;
        }
    }
}
