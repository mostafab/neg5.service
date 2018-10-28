package org.neg5.mappers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.neg5.TournamentTeamDTO;
import org.neg5.data.TournamentTeam;

import java.util.HashSet;
import java.util.stream.Collectors;

@Singleton
public class TournamentTeamMapper extends AbstractObjectMapper<TournamentTeam, TournamentTeamDTO> {

    @Inject private TournamentDivisionMapper divisionMapper;
    @Inject private TournamentPlayerMapper playerMapper;

    protected TournamentTeamMapper() {
        super(TournamentTeam.class, TournamentTeamDTO.class);
    }

    @Override
    protected void enrichDTO(TournamentTeamDTO tournamentTeamDTO, TournamentTeam tournamentTeam) {
        tournamentTeamDTO.setDivisions(tournamentTeam.getDivisions() == null ? new HashSet<>() : tournamentTeam.getDivisions().stream()
                .map(divisionMapper::toDTO).collect(Collectors.toSet()));
        tournamentTeamDTO.setPlayers(tournamentTeam.getPlayers() == null
                ? new HashSet<>()
                : tournamentTeam.getPlayers().stream()
                    .map(playerMapper::toDTO)
                    .collect(Collectors.toSet())
        );
    }
}
