package org.neg5.data.embeddables;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class MatchTeamId implements Serializable {

    private String teamId;
    private String matchId;
    private String tournamentId;

    @Column(name = "team_id")
    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
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
        if (obj == null || !(obj instanceof MatchTeamId)) {
            return false;
        }
        MatchTeamId that = (MatchTeamId) obj;
        return that.getTeamId().equals(teamId)
                && that.getTournamentId().equals(tournamentId)
                && that.getMatchId().equals(matchId);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 3)
                .append(teamId)
                .append(tournamentId)
                .append(matchId)
                .toHashCode();
    }
}
