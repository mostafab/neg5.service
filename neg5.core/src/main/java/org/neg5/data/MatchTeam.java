package org.neg5.data;

import org.hibernate.annotations.DynamicUpdate;
import org.neg5.data.embeddables.MatchTeamId;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "team_plays_in_tournament_match")
@DynamicUpdate
public class MatchTeam extends AbstractDataObject<MatchTeam, MatchTeamId> {

    private MatchTeamId matchTeamId;

    private Integer score;
    private Integer bouncebackPoints;
    private Integer overtimeTossupsGotten;

    /*
    Since this table has a composite primary key, we need to use an {@link EmbeddedId} to represent it
     */
    @EmbeddedId
    public MatchTeamId getId() {
        return matchTeamId;
    }

    public void setId(MatchTeamId matchTeamId) {
        this.matchTeamId = matchTeamId;
    }

    @Column(name = "score")
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Column(name = "bounceback_points")
    public Integer getBouncebackPoints() {
        return bouncebackPoints;
    }

    public void setBouncebackPoints(Integer bouncebackPoints) {
        this.bouncebackPoints = bouncebackPoints;
    }

    @Column(name = "overtime_tossups_gotten")
    public Integer getOvertimeTossupsGotten() {
        return overtimeTossupsGotten;
    }

    public void setOvertimeTossupsGotten(Integer overtimeTossupsGotten) {
        this.overtimeTossupsGotten = overtimeTossupsGotten;
    }
}
