package org.neg5.mappers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.neg5.TournamentMatchDTO;
import org.neg5.data.TournamentMatch;
import org.neg5.data.TournamentPhase;

import java.util.stream.Collectors;

@Singleton
public class TournamentMatchMapper extends AbstractObjectMapper<TournamentMatch, TournamentMatchDTO> {

    @Inject private MatchTeamMapper matchTeamMapper;

    protected TournamentMatchMapper() {
        super(TournamentMatch.class, TournamentMatchDTO.class);
    }

    @Override
    protected void enrichDTO(TournamentMatchDTO tournamentMatchDTO, TournamentMatch tournamentMatch) {
        tournamentMatchDTO.setTeams(tournamentMatch.getTeams().stream().map(matchTeamMapper::toDTO).collect(Collectors.toSet()));
        tournamentMatchDTO.setPhases(tournamentMatch.getPhases().stream()
                .map(TournamentPhase::getId).collect(Collectors.toSet()));

        tournamentMatchDTO.setTournamentId(tournamentMatch.getTournament().getId());
    }
}
