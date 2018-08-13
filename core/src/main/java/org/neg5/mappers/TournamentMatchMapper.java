package org.neg5.mappers;

import com.google.inject.Inject;
import org.neg5.TournamentMatchDTO;
import org.neg5.data.TournamentMatch;

import java.util.stream.Collectors;

public class TournamentMatchMapper extends AbstractObjectMapper<TournamentMatch, TournamentMatchDTO> {

    @Inject private MatchTeamMapper matchTeamMapper;

    protected TournamentMatchMapper() {
        super(TournamentMatch.class, TournamentMatchDTO.class);
    }

    @Override
    public TournamentMatchDTO toDTO(TournamentMatch tournamentMatch) {
        TournamentMatchDTO dto = super.toDTO(tournamentMatch);
        dto.setTeams(tournamentMatch.getTeams().stream().map(matchTeamMapper::toDTO).collect(Collectors.toList()));

        return dto;
    }

    @Override
    protected void addMappings() {
        getTypeMap().addMappings(mapper -> {
            mapper.map(match -> match.getTournament().getId(), TournamentMatchDTO::setTournamentId);
            mapper.map(match -> match.getAddedBy().getUsername(), TournamentMatchDTO::setAddedBy);
        });
    }
}
