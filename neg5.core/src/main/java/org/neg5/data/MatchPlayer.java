package org.neg5.data;

import org.hibernate.annotations.DynamicUpdate;
import org.neg5.data.embeddables.MatchPlayerId;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.Set;

@Table(name = "player_plays_in_tournament_match")
@Entity
@DynamicUpdate
public class MatchPlayer extends AbstractDataObject<MatchPlayer, MatchPlayerId> {

    private MatchPlayerId matchPlayerId;
    private Integer tossupsHeard;

    private Set<MatchPlayerAnswer> answers;

    private TournamentPlayer player;
    private TournamentMatch match;

    /*
    Since this table has a composite primary key, we need to use an {@link EmbeddedId} to represent it
     */
    @EmbeddedId
    public MatchPlayerId getId() {
        return matchPlayerId;
    }

    public void setId(MatchPlayerId matchPlayerId) {
        this.matchPlayerId = matchPlayerId;
    }

    @Column(name = "tossups_heard")
    public Integer getTossupsHeard() {
        return tossupsHeard;
    }

    public void setTossupsHeard(Integer tossupsHeard) {
        this.tossupsHeard = tossupsHeard;
    }

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "player_id", referencedColumnName = "player_id", updatable = false, insertable = false),
            @JoinColumn(name = "match_id", referencedColumnName = "match_id", updatable = false, insertable = false),
            @JoinColumn(name = "tournament_id", referencedColumnName = "tournament_id", updatable = false, insertable = false)
    })
    public Set<MatchPlayerAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<MatchPlayerAnswer> answers) {
        this.answers = answers;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id", nullable = false, updatable = false, insertable = false)
    public TournamentPlayer getPlayer() {
        return player;
    }

    public void setPlayer(TournamentPlayer player) {
        this.player = player;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id", nullable = false, updatable = false, insertable = false)
    public TournamentMatch getMatch() {
        return match;
    }

    public void setMatch(TournamentMatch match) {
        this.match = match;
    }
}
