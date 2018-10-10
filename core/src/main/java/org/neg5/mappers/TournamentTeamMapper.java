package org.neg5.mappers;

import org.neg5.TournamentTeamDTO;
import org.neg5.data.TournamentDivision;
import org.neg5.data.TournamentTeam;

import java.util.stream.Collectors;

public class TournamentTeamMapper extends AbstractObjectMapper<TournamentTeam, TournamentTeamDTO> {

    protected TournamentTeamMapper() {
        super(TournamentTeam.class, TournamentTeamDTO.class);
    }

    @Override
    public TournamentTeamDTO toDTO(TournamentTeam tournamentTeam) {
        TournamentTeamDTO dto = super.toDTO(tournamentTeam);
        dto.setDivisions(tournamentTeam.getDivisions().stream()
            .map(TournamentDivision::getId).collect(Collectors.toSet()));
        return dto;
    }

    @Override
    protected void addMappings() {
        getTypeMap().addMappings(mapper -> {
           mapper.map(team -> team.getTournament().getId(), TournamentTeamDTO::setTournamentId);
        });
    }
}
