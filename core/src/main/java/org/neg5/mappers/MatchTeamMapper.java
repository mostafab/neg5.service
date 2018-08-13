package org.neg5.mappers;

import com.google.inject.Inject;
import org.neg5.MatchPlayerDTO;
import org.neg5.MatchTeamDTO;
import org.neg5.data.MatchPlayer;
import org.neg5.data.MatchTeam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MatchTeamMapper extends AbstractObjectMapper<MatchTeam, MatchTeamDTO> {

    @Inject private MatchPlayerMapper matchPlayerMapper;

    protected MatchTeamMapper() {
        super(MatchTeam.class, MatchTeamDTO.class);
    }

    @Override
    public MatchTeamDTO toDTO(MatchTeam matchTeam) {
        MatchTeamDTO dto = super.toDTO(matchTeam);
        List<MatchPlayer> players = Optional.ofNullable(matchTeam.getMatchTeamId().getMatch().getPlayers())
                .orElse(new ArrayList<>());
        List<MatchPlayerDTO> playersOnTeam = players.stream()
                .filter(p -> p.getMatchPlayerId().getPlayer().getTeam().getId().equals(dto.getTeamId()))
                .map(matchPlayerMapper::toDTO)
                .collect(Collectors.toList());
        dto.setPlayers(playersOnTeam);
        return dto;
    }

    @Override
    protected void addMappings() {
        getTypeMap().addMappings(mapper -> {
           mapper.map(entity -> entity.getMatchTeamId().getMatch().getId(), MatchTeamDTO::setMatchId);
           mapper.map(entity -> entity.getMatchTeamId().getTeam().getId(), MatchTeamDTO::setTeamId);
        });
    }
}
