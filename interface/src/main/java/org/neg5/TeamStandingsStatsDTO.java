package org.neg5;

import java.util.List;

public class TeamStandingsStatsDTO {

    private List<TeamStandingStatsDTO> teamStandings;
    private String phaseId;
    private String tournamentId;

    public List<TeamStandingStatsDTO> getTeamStandings() {
        return teamStandings;
    }

    public void setTeamStandings(List<TeamStandingStatsDTO> teamStandings) {
        this.teamStandings = teamStandings;
    }

    public String getPhaseId() {
        return phaseId;
    }

    public void setPhaseId(String phaseId) {
        this.phaseId = phaseId;
    }

    public String getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(String tournamentId) {
        this.tournamentId = tournamentId;
    }
}
