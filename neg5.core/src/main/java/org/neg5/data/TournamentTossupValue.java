package org.neg5.data;

import org.hibernate.annotations.DynamicUpdate;
import org.neg5.data.embeddables.TournamentTossupValueId;
import org.neg5.enums.TossupAnswerType;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tournament_tossup_values")
@DynamicUpdate
public class TournamentTossupValue
        extends AbstractDataObject<TournamentTossupValue, TournamentTossupValueId>
        implements SpecificTournamentEntity {

    private TournamentTossupValueId tournamentTossupValueId;
    private TossupAnswerType answerType;

    @EmbeddedId
    public TournamentTossupValueId getId() {
        return tournamentTossupValueId;
    }

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
    @Transient
    public Tournament getTournament() {
        return tournamentTossupValueId.getTournament();
    }
}
