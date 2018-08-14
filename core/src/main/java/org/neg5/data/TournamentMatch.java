package org.neg5.data;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tournament_match")
@DynamicUpdate
public class TournamentMatch extends AbstractDataObject<TournamentMatch> implements SpecificTournamentEntity {

    private String id;
    private Tournament tournament;

    private Long round;
    private String room;
    private String moderator;

    private String packet;
    private Integer tossupsHeard;

    private String notes;
    private String serialId;

    private Account addedBy;

    private Date addedAt;
    private Date lastUpdatedAt;

    private List<MatchTeam> teams;
    private List<MatchPlayer> players;
    private List<MatchPlayerAnswer> playerAnswers;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tournament_id")
    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    @Column(name = "round")
    public Long getRound() {
        return round;
    }

    public void setRound(Long round) {
        this.round = round;
    }

    @Column(name = "room")
    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    @Column(name = "moderator")
    public String getModerator() {
        return moderator;
    }

    public void setModerator(String moderator) {
        this.moderator = moderator;
    }

    @Column(name = "packet")
    public String getPacket() {
        return packet;
    }

    public void setPacket(String packet) {
        this.packet = packet;
    }

    @Column(name = "tossups_heard")
    public Integer getTossupsHeard() {
        return tossupsHeard;
    }

    public void setTossupsHeard(Integer tossupsHeard) {
        this.tossupsHeard = tossupsHeard;
    }

    @Column(name = "notes")
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Column(name = "serial_id")
    public String getSerialId() {
        return serialId;
    }

    public void setSerialId(String serialId) {
        this.serialId = serialId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "added_by")
    public Account getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(Account addedBy) {
        this.addedBy = addedBy;
    }

    @Column(name = "added_at")
    public Date getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(Date addedAt) {
        this.addedAt = addedAt;
    }

    @Column(name = "last_updated_at")
    public Date getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(Date lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "matchTeamId.match")
    public List<MatchTeam> getTeams() {
        return teams;
    }

    public void setTeams(List<MatchTeam> teams) {
        this.teams = teams;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "matchPlayerId.match")
    public List<MatchPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(List<MatchPlayer> players) {
        this.players = players;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "matchPlayerAnswerId.match")
    public List<MatchPlayerAnswer> getPlayerAnswers() {
        return playerAnswers;
    }

    public void setPlayerAnswers(List<MatchPlayerAnswer> playerAnswers) {
        this.playerAnswers = playerAnswers;
    }
}