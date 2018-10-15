package org.neg5.managers.stats.aggregators;

import org.neg5.IndividualStandingStatDTO;
import org.neg5.MatchPlayerDTO;
import org.neg5.TournamentMatchDTO;

public class IndividualStandingStatAggregator implements StatAggregator<IndividualStandingStatDTO> {

    private final String playerId;

    public IndividualStandingStatAggregator(String playerId) {
        this.playerId = playerId;
    }

    @Override
    public void accept(TournamentMatchDTO match) {
        MatchPlayerDTO player = getPlayer(match);
    }

    @Override
    public IndividualStandingStatDTO collect() {
        return new IndividualStandingStatDTO();
    }

    private MatchPlayerDTO getPlayer(TournamentMatchDTO match) {
        return match.getTeams().stream()
                .flatMap(t -> t.getPlayers().stream())
                .filter(player -> playerId.equals(player.getPlayerId()))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Cannot find player " + playerId + " in match " + match.getId()));
    }
}
