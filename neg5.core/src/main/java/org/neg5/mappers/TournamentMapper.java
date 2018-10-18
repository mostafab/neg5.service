package org.neg5.mappers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.neg5.TournamentDTO;
import org.neg5.data.Tournament;

import java.util.stream.Collectors;

@Singleton
public class TournamentMapper extends AbstractObjectMapper<Tournament, TournamentDTO> {

    @Inject private TournamentPhaseMapper tournamentPhaseMapper;
    @Inject private TournamentDivisionMapper tournamentDivisionMapper;
    @Inject private TournamentTossupValueMapper tournamentTossupValueMapper;

    protected TournamentMapper() {
        super(Tournament.class, TournamentDTO.class);
    }

    @Override
    public TournamentDTO toDTO(Tournament tournament) {
        TournamentDTO dto = super.toDTO(tournament);
        dto.setPhases(tournament.getPhases().stream()
            .map(tournamentPhaseMapper::toDTO).collect(Collectors.toSet()));
        dto.setDivisions(tournament.getDivisions().stream()
            .map(tournamentDivisionMapper::toDTO).collect(Collectors.toSet()));
        dto.setTossupValues(tournament.getTossupValues().stream()
            .map(tournamentTossupValueMapper::toDTO).collect(Collectors.toSet()));

        if (tournament.getCurrentPhase() != null) {
            dto.setCurrentPhaseId(tournament.getCurrentPhase().getId());
        }
        return dto;
    }
}
