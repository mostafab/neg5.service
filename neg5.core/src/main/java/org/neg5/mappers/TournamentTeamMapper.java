package org.neg5.mappers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.neg5.TournamentTeamDTO;
import org.neg5.data.TournamentTeam;

import java.util.stream.Collectors;

@Singleton
public class TournamentTeamMapper extends AbstractObjectMapper<TournamentTeam, TournamentTeamDTO> {

    @Inject private TournamentDivisionMapper divisionMapper;
    @Inject private TournamentPlayerMapper playerMapper;

    protected TournamentTeamMapper() {
        super(TournamentTeam.class, TournamentTeamDTO.class);
    }

    @Override
    public TournamentTeamDTO toDTO(TournamentTeam tournamentTeam) {
        TournamentTeamDTO dto = super.toDTO(tournamentTeam);
        dto.setDivisions(tournamentTeam.getDivisions().stream()
            .map(divisionMapper::toDTO).collect(Collectors.toSet()));
        dto.setPlayers(tournamentTeam.getPlayers().stream().map(playerMapper::toDTO).collect(Collectors.toSet()));
        return dto;
    }

    @Override
    protected void addMappings() {
        getTypeMap().addMappings(mapper -> {
           mapper.map(team -> team.getTournament().getId(), TournamentTeamDTO::setTournamentId);
        });
    }
}
