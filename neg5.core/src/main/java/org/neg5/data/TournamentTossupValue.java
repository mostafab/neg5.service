package org.neg5.data;

import org.hibernate.annotations.DynamicUpdate;
import org.neg5.data.embeddables.TournamentTossupValueId;
import org.neg5.enums.TossupAnswerType;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tournament_tossup_values")
@DynamicUpdate
public class TournamentTossupValue {

    private TournamentTossupValueId tournamentTossupValueId;
    private TossupAnswerType answerType;

    @EmbeddedId
    public TournamentTossupValueId getTournamentTossupValueId() {
        return tournamentTossupValueId;
    }

    public void setTournamentTossupValueId(TournamentTossupValueId tournamentTossupValueId) {
        this.tournamentTossupValueId = tournamentTossupValueId;
    }

    @Column(name = "tossup_answer_type")
    public TossupAnswerType getAnswerType() {
        return answerType;
    }

    public void setAnswerType(TossupAnswerType answerType) {
        this.answerType = answerType;
    }
}
