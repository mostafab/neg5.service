package org.neg5.data;

import org.hibernate.annotations.DynamicUpdate;
import org.neg5.data.embeddables.MatchPlayerAnswerId;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "player_match_tossup")
@DynamicUpdate
public class MatchPlayerAnswer extends AbstractDataObject<MatchPlayerAnswer, MatchPlayerAnswerId> {

    private MatchPlayerAnswerId matchPlayerAnswerId;

    private TournamentTossupValue tournamentTossupValue;
    private Integer numberGotten;

    @EmbeddedId
    public MatchPlayerAnswerId getId() {
        return matchPlayerAnswerId;
    }

    public void setId(MatchPlayerAnswerId matchPlayerAnswerId) {
        this.matchPlayerAnswerId = matchPlayerAnswerId;
    }

    @Column(name = "number_gotten")
    public Integer getNumberGotten() {
        return numberGotten;
    }

    public void setNumberGotten(Integer numberGotten) {
        this.numberGotten = numberGotten;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "tournament_id", insertable = false, updatable = false),
            @JoinColumn(name = "tossup_value", insertable = false, updatable = false)
    })
    public TournamentTossupValue getTournamentTossupValue() {
        return tournamentTossupValue;
    }

    public void setTournamentTossupValue(TournamentTossupValue tournamentTossupValue) {
        this.tournamentTossupValue = tournamentTossupValue;
    }
}
