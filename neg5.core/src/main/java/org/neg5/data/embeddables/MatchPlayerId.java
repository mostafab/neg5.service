package org.neg5.data.embeddables;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class MatchPlayerId implements Serializable {

    private String playerId;
    private String matchId;
    private String tournamentId;

    @Column(name = "player_id")
    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    @Column(name = "match_id")
    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    @Column(name = "tournament_id")
    public String getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(String tournamentId) {
        this.tournamentId = tournamentId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof MatchPlayerId)) {
            return false;
        }
        MatchPlayerId that = (MatchPlayerId) obj;
        return that.getPlayerId().equals(playerId)
                && that.getTournamentId().equals(tournamentId)
                && that.getMatchId().equals(matchId);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 3)
                .append(playerId)
                .append(matchId)
                .append(tournamentId)
                .toHashCode();
    }
}
