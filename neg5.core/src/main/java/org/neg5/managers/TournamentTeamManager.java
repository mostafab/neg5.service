package org.neg5.managers;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.neg5.MatchTeamDTO;
import org.neg5.TournamentMatchDTO;
import org.neg5.TournamentTeamDTO;
import org.neg5.daos.TournamentTeamDAO;
import org.neg5.data.TournamentTeam;

import org.neg5.mappers.TournamentTeamMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Singleton
public class TournamentTeamManager extends AbstractDTOManager<TournamentTeam, TournamentTeamDTO, String> {

    @Inject private TournamentTeamDAO tournamentTeamDAO;

    @Inject private TournamentTeamMapper tournamentTeamMapper;

    @Inject private TournamentMatchManager tournamentMatchManager;

    @Override
    protected TournamentTeamDAO getDAO() {
        return tournamentTeamDAO;
    }

    @Override
    protected TournamentTeamMapper getMapper() {
        return tournamentTeamMapper;
    }

    @Override
    protected String getIdFromDTO(TournamentTeamDTO tournamentTeamDTO) {
        return tournamentTeamDTO.getId();
    }

    /**
     * Group matches by teams, where the key is the team's id and the value is the list of matches the team is part of
     * @param tournamentId tournamentId
     * @param phaseId phaseId
     * @return mapping between tean -> matches
     */
    public Map<String, List<TournamentMatchDTO>> groupTeamsByMatches(String tournamentId,
                                                                     String phaseId) {
        List<TournamentMatchDTO> matches = tournamentMatchManager.findAllByTournamentAndPhase(tournamentId, phaseId);
        Map<String, List<TournamentMatchDTO>> matchesByTeamId = new HashMap<>();
        matches.forEach(match -> {
            Set<MatchTeamDTO> teams = match.getTeams();
            teams.forEach(team -> {
                matchesByTeamId.computeIfPresent(team.getTeamId(), (id, list) -> {
                    list.add(match);
                    return list;
                });
                matchesByTeamId.computeIfAbsent(team.getTeamId(), teamId -> Lists.newArrayList(match));
            });
        });
        List<TournamentTeamDTO> allTeams = findAllByTournamentId(tournamentId);
        allTeams.forEach(team -> matchesByTeamId.putIfAbsent(team.getId(), new ArrayList<>()));
        return matchesByTeamId;
    }
}
