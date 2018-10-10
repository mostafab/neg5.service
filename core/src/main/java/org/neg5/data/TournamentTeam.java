package org.neg5.data;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "tournament_team")
@DynamicUpdate
public class TournamentTeam extends AbstractDataObject<TournamentTeam> implements SpecificTournamentEntity {

    private String id;

    private String name;
    private Tournament tournament;

    private Set<TournamentDivision> divisions;

    @Id
    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tournament_id")
    @Override
    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tournament_team_in_division",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "division_id")
    )
    public Set<TournamentDivision> getDivisions() {
        return divisions;
    }

    public void setDivisions(Set<TournamentDivision> divisions) {
        this.divisions = divisions;
    }
}
