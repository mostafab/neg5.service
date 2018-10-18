package org.neg5.data.embeddables;

import org.neg5.data.Tournament;
import org.neg5.data.TournamentMatch;
import org.neg5.data.TournamentPlayer;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class MatchPlayerId implements Serializable {

    private TournamentPlayer player;
    private TournamentMatch match;
    private Tournament tournament;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    public TournamentPlayer getPlayer() {
        return player;
    }

    public void setPlayer(TournamentPlayer player) {
        this.player = player;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id")
    public TournamentMatch getMatch() {
        return match;
    }

    public void setMatch(TournamentMatch match) {
        this.match = match;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tournament_id")
    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
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
        return that.getPlayer().getId().equals(player.getId())
                && that.getTournament().getId().equals(tournament.getId())
                && that.getMatch().getId().equals(match.getId());
    }


    /**
     * TODO come back and implement
     * @return a hash code
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
