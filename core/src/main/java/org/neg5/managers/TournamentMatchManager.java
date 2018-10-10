package org.neg5.managers;

import com.google.inject.Inject;
import org.neg5.TournamentMatchDTO;
import org.neg5.daos.TournamentMatchDAO;
import org.neg5.data.TournamentMatch;
import org.neg5.mappers.TournamentMatchMapper;

import java.util.List;
import java.util.stream.Collectors;

public class TournamentMatchManager extends AbstractManager<TournamentMatch, TournamentMatchDTO> {

    @Inject private TournamentMatchMapper tournamentMatchMapper;
    @Inject private TournamentMatchDAO tournamentMatchDAO;

    @Override
    protected TournamentMatchMapper getMapper() {
        return tournamentMatchMapper;
    }

    @Override
    protected TournamentMatchDAO getDAO() {
        return tournamentMatchDAO;
    }

    public List<TournamentMatchDTO> findAllByTournamentAndPhase(String tournamentId, String phaseId) {
        return findAllByTournamentId(tournamentId).stream()
                .filter(match -> phaseId == null || match.getPhases().contains(phaseId))
                .collect(Collectors.toList());
    }
}
