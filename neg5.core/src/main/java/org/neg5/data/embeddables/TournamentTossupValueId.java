package org.neg5.data.embeddables;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class TournamentTossupValueId implements Serializable {

    private String tournamentId;
    private Integer value;

    @Column(name = "tournament_id")
    public String getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(String tournamentId) {
        this.tournamentId = tournamentId;
    }

    @Column(name = "tossup_value")
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof TournamentTossupValueId)) {
            return false;
        }
        TournamentTossupValueId that = (TournamentTossupValueId) obj;
        return that.getTournamentId().equals(tournamentId)
                && that.getValue().equals(value);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 3)
                .append(tournamentId)
                .append(value)
                .toHashCode();
    }
}
