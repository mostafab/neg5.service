package org.neg5.managers;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.neg5.MatchPlayerDTO;
import org.neg5.TournamentMatchDTO;
import org.neg5.TournamentPlayerDTO;

import org.neg5.core.ReadOnly;
import org.neg5.daos.TournamentPlayerDAO;
import org.neg5.data.TournamentPlayer;
import org.neg5.mappers.TournamentPlayerMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Singleton
public class TournamentPlayerManager extends AbstractDTOManager<TournamentPlayer, TournamentPlayerDTO, String> {

    @Inject private TournamentPlayerMapper tournamentPlayerMapper;
    @Inject @ReadOnly private TournamentPlayerDAO tournamentPlayerDAO;

    @Inject private TournamentMatchManager tournamentMatchManager;

    @Override
    protected TournamentPlayerMapper getMapper() {
        return tournamentPlayerMapper;
    }

    @Override
    protected TournamentPlayerDAO getDAO() {
        return tournamentPlayerDAO;
    }

    @Override
    protected String getIdFromDTO(TournamentPlayerDTO tournamentPlayerDTO) {
        return tournamentPlayerDTO.getId();
    }

    /**
     * Group matches by players, where the key is the player's id and the value is the list of matches the player is part of
     * @param tournamentId tournamentId
     * @param phaseId phaseId
     * @return mapping between player -> matches
     */
    public Map<String, List<TournamentMatchDTO>> groupPlayersByMatches(String tournamentId, String phaseId) {
        List<TournamentMatchDTO> matches = tournamentMatchManager.findAllByTournamentAndPhase(tournamentId, phaseId);
        Map<String, List<TournamentMatchDTO>> matchesByPlayerId = new HashMap<>();
        matches.forEach(match -> {
            Set<MatchPlayerDTO> players = match.getTeams().stream()
                    .flatMap(team -> team.getPlayers().stream())
                    .collect(Collectors.toSet());
            players.forEach(player -> {
                matchesByPlayerId.computeIfPresent(player.getPlayerId(), (id, list) -> {
                    list.add(match);
                    return list;
                });
                matchesByPlayerId.computeIfAbsent(player.getPlayerId(), teamId -> Lists.newArrayList(match));
            });
        });
        List<TournamentPlayerDTO> allPlayers = findAllByTournamentId(tournamentId);
        allPlayers.forEach(player -> matchesByPlayerId.putIfAbsent(player.getId(), new ArrayList<>()));
        return matchesByPlayerId;
    }
}