package org.neg5.data;

import org.hibernate.annotations.DynamicUpdate;
import org.neg5.data.embeddables.MatchPlayerAnswerId;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "player_match_tossup")
@DynamicUpdate
public class MatchPlayerAnswer {

    private MatchPlayerAnswerId matchPlayerAnswerId;

    private Integer numberGotten;

    @EmbeddedId
    public MatchPlayerAnswerId getMatchPlayerAnswerId() {
        return matchPlayerAnswerId;
    }

    public void setMatchPlayerAnswerId(MatchPlayerAnswerId matchPlayerAnswerId) {
        this.matchPlayerAnswerId = matchPlayerAnswerId;
    }

    @Column(name = "number_gotten")
    public Integer getNumberGotten() {
        return numberGotten;
    }

    public void setNumberGotten(Integer numberGotten) {
        this.numberGotten = numberGotten;
    }
}
