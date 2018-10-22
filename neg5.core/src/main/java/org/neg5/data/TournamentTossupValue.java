package org.neg5.data;

import org.hibernate.annotations.DynamicUpdate;
import org.neg5.data.embeddables.TournamentTossupValueId;
import org.neg5.enums.TossupAnswerType;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tournament_tossup_values")
@DynamicUpdate
public class TournamentTossupValue
        extends AbstractDataObject<TournamentTossupValue>
        implements SpecificTournamentEntity, IdDataObject<TournamentTossupValueId> {

    private TournamentTossupValueId tournamentTossupValueId;
    private TossupAnswerType answerType;

    private Tournament tournament;

    @EmbeddedId
    @Override
    public TournamentTossupValueId getId() {
        return tournamentTossupValueId;
    }

    @Override
    public void setId(TournamentTossupValueId tournamentTossupValueId) {
        this.tournamentTossupValueId = tournamentTossupValueId;
    }

    @Column(name = "tossup_answer_type")
    public TossupAnswerType getAnswerType() {
        return answerType;
    }

    public void setAnswerType(TossupAnswerType answerType) {
        this.answerType = answerType;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tournament_id", updatable = false, insertable = false)
    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }
}
