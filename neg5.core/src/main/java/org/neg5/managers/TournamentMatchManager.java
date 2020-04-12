package org.neg5.managers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;
import org.neg5.TournamentMatchDTO;
import org.neg5.TournamentTossupValueDTO;
import org.neg5.daos.TournamentMatchDAO;
import org.neg5.data.TournamentMatch;
import org.neg5.data.transformers.data.Match;
import org.neg5.mappers.TournamentMatchMapper;
import org.neg5.mappers.data.MatchToMatchDTOMapper;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Singleton
public class TournamentMatchManager extends AbstractDTOManager<TournamentMatch, TournamentMatchDTO, String> {

    @Inject private TournamentManager tournamentManager;
    @Inject private MatchTeamManager matchTeamManager;

    @Inject private TournamentMatchMapper tournamentMatchMapper;
    @Inject private MatchToMatchDTOMapper matchToMatchDTOMapper;

    @Inject private TournamentMatchDAO rwTournamentMatchDAO;

    @Override
    protected TournamentMatchMapper getMapper() {
        return tournamentMatchMapper;
    }

    @Override
    protected TournamentMatchDAO getRwDAO() {
        return rwTournamentMatchDAO;
    }

    @Override
    protected String getIdFromDTO(TournamentMatchDTO tournamentMatchDTO) {
        return tournamentMatchDTO.getId();
    }

    @Override
    @Transactional
    public TournamentMatchDTO create(TournamentMatchDTO match) {
        TournamentMatchDTO createdMatch = super.create(match);
        createdMatch.setTeams(match.getTeams().stream()
                .map(matchTeam -> {
                    matchTeam.setTournamentId(match.getTournamentId());
                    matchTeam.setMatchId(createdMatch.getId());
                    return matchTeamManager.create(matchTeam);
                })
                .collect(Collectors.toSet()));

        return createdMatch;
    }

    @Override
    public List<TournamentMatchDTO> findAllByTournamentId(String tournamentId) {
        Map<Integer, TournamentTossupValueDTO> tossupValues = getTossupValueMap(tournamentId);
        return findByRawQuery(tournamentId).stream()
                .map(match -> matchToMatchDTOMapper.toDTO(match, tossupValues))
                .collect(Collectors.toList());
    }

    @Transactional
    public List<TournamentMatchDTO> findAllByTournamentAndPhase(String tournamentId, String phaseId) {
        return findAllByTournamentId(tournamentId).stream()
                .filter(match -> phaseId == null || match.getPhases().contains(phaseId))
                .collect(Collectors.toList());
    }

    @Transactional
    protected List<Match> findByRawQuery(String tournamentId) {
        return getRwDAO().findMatchesByTournamentIdWithRawQuery(tournamentId);
    }

    private Map<Integer, TournamentTossupValueDTO> getTossupValueMap(String tournamentId) {
        return tournamentManager.get(tournamentId).getTossupValues().stream()
                .collect(Collectors.toMap(TournamentTossupValueDTO::getValue, Function.identity()));
    }
}
