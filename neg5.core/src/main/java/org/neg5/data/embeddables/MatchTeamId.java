package org.neg5.data.embeddables;

import org.neg5.data.Tournament;
import org.neg5.data.TournamentMatch;
import org.neg5.data.TournamentTeam;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class MatchTeamId implements Serializable {

    private TournamentTeam team;
    private TournamentMatch match;
    private Tournament tournament;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    public TournamentTeam getTeam() {
        return team;
    }

    public void setTeam(TournamentTeam team) {
        this.team = team;
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
        if (obj == null || !(obj instanceof MatchTeamId)) {
            return false;
        }
        MatchTeamId that = (MatchTeamId) obj;
        return that.getTeam().getId().equals(team.getId())
                && that.getTournament().getId().equals(tournament.getId())
                && that.getMatch().getId().equals(match.getId());
    }

    /**
     * TODO come back and implement this
     * @return a hash code
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
