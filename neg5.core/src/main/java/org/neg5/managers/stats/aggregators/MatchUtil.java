package org.neg5.managers.stats.aggregators;

import org.neg5.MatchPlayerDTO;
import org.neg5.MatchTeamDTO;
import org.neg5.TournamentMatchDTO;

import java.util.Optional;

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
        Optional<MatchTeamDTO> otherTeam = match.getTeams().stream()
                .filter(team -> !teamId.equals(team.getTeamId()))
                .findFirst();

        return new TeamsWrapper(thisTeam, otherTeam);
    }

    public static MatchPlayerDTO getPlayer(String playerId, TournamentMatchDTO match) {
        return match.getTeams().stream()
                .flatMap(t -> t.getPlayers().stream())
                .filter(player -> playerId.equals(player.getPlayerId()))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Cannot find player " + playerId + " in match " + match.getId()));
    }

    final static class TeamsWrapper {

        private final MatchTeamDTO thisTeam;
        private final Optional<MatchTeamDTO> otherTeam;

        TeamsWrapper(MatchTeamDTO thisTeam, Optional<MatchTeamDTO> otherTeam) {
            this.thisTeam = thisTeam;
            this.otherTeam = otherTeam;
        }

        MatchTeamDTO getThisTeam() {
            return thisTeam;
        }

        Optional<MatchTeamDTO> getOtherTeam() {
            return otherTeam;
        }
    }
}
