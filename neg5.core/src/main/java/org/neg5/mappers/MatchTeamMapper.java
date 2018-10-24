package org.neg5.mappers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.neg5.MatchPlayerDTO;
import org.neg5.MatchTeamDTO;
import org.neg5.data.MatchPlayer;
import org.neg5.data.MatchTeam;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Singleton
public class MatchTeamMapper extends AbstractObjectMapper<MatchTeam, MatchTeamDTO> {

    @Inject private MatchPlayerMapper matchPlayerMapper;

    protected MatchTeamMapper() {
        super(MatchTeam.class, MatchTeamDTO.class);
    }

    @Override
    protected void enrichDTO(MatchTeamDTO matchTeamDTO, MatchTeam matchTeam) {
        Set<MatchPlayer> players = Optional.ofNullable(matchTeam.getMatch().getPlayers())
                .orElse(new HashSet<>());
        List<MatchPlayerDTO> playersOnTeam = players.stream()
                .filter(p -> p.getPlayer().getTeam().getId().equals(matchTeam.getTeam().getId()))
                .map(matchPlayerMapper::toDTO)
                .collect(Collectors.toList());
        matchTeamDTO.setPlayers(playersOnTeam);
    }
}
