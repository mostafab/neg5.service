package org.neg5.mappers.data;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.neg5.TournamentMatchDTO;
import org.neg5.data.transformers.data.Match;
import org.neg5.data.transformers.data.Phase;
import org.neg5.mappers.AbstractObjectMapper;

import java.util.stream.Collectors;

@Singleton
public class MatchToMatchDTOMapper extends AbstractObjectMapper<Match, TournamentMatchDTO> {

    @Inject private TeamInMatchMapper teamInMatchMapper;

    protected MatchToMatchDTOMapper() {
        super(Match.class, TournamentMatchDTO.class);
    }

    @Override
    public TournamentMatchDTO toDTO(Match match) {
        TournamentMatchDTO dto = super.toDTO(match);
        dto.setPhases(match.getPhases().stream().map(Phase::getId).collect(Collectors.toSet()));
        dto.setTeams(match.getTeams().stream().map(teamInMatchMapper::toDTO).collect(Collectors.toSet()));
        return dto;
    }
}
