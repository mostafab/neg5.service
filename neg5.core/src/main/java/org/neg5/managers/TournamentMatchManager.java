package org.neg5.managers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.neg5.TournamentMatchDTO;
import org.neg5.TournamentTossupValueDTO;
import org.neg5.core.ReadOnly;
import org.neg5.core.ReadWrite;
import org.neg5.core.Transactional;
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

    @Inject private TournamentMatchMapper tournamentMatchMapper;
    @Inject private MatchToMatchDTOMapper matchToMatchDTOMapper;

    @Inject @ReadWrite private TournamentMatchDAO rwTournamentMatchDAO;
    @Inject @ReadOnly private TournamentMatchDAO roTournamentMatchDAO;

    @Override
    protected TournamentMatchMapper getMapper() {
        return tournamentMatchMapper;
    }

    @Override
    protected TournamentMatchDAO getRwDAO() {
        return rwTournamentMatchDAO;
    }

    @Override
    protected TournamentMatchDAO getRoDAO() {
        return roTournamentMatchDAO;
    }

    @Override
    protected String getIdFromDTO(TournamentMatchDTO tournamentMatchDTO) {
        return tournamentMatchDTO.getId();
    }

    @Override
    public List<TournamentMatchDTO> findAllByTournamentId(String tournamentId) {
        Map<Integer, TournamentTossupValueDTO> tossupValues = getTossupValueMap(tournamentId);
        return findByRawQuery(tournamentId).stream()
                .map(match -> matchToMatchDTOMapper.toDTO(match, tossupValues))
                .collect(Collectors.toList());
    }

    @Transactional(readWrite = false)
    public List<TournamentMatchDTO> findAllByTournamentAndPhase(String tournamentId, String phaseId) {
        return findAllByTournamentId(tournamentId).stream()
                .filter(match -> phaseId == null || match.getPhases().contains(phaseId))
                .collect(Collectors.toList());
    }

    @Transactional(readWrite = false)
    protected List<Match> findByRawQuery(String tournamentId) {
        return getRoDAO().findMatchesByTournamentIdWithRawQuery(tournamentId);
    }

    private Map<Integer, TournamentTossupValueDTO> getTossupValueMap(String tournamentId) {
        return tournamentManager.get(tournamentId).getTossupValues().stream()
                .collect(Collectors.toMap(TournamentTossupValueDTO::getValue, Function.identity()));
    }
}
