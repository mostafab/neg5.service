package org.neg5.managers;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import org.neg5.MatchTeamDTO;
import org.neg5.daos.MatchTeamDAO;
import org.neg5.data.MatchTeam;
import org.neg5.data.embeddables.MatchTeamId;
import org.neg5.mappers.MatchTeamMapper;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class MatchTeamManager extends AbstractDTOManager<MatchTeam, MatchTeamDTO, MatchTeamId> {

    private final MatchTeamDAO matchTeamDAO;
    private final MatchTeamMapper matchTeamMapper;
    private final MatchPlayerManager matchPlayerManager;

    @Inject
    public MatchTeamManager(MatchTeamDAO matchTeamDAO,
                            MatchTeamMapper matchTeamMapper,
                            MatchPlayerManager matchPlayerManager) {
        this.matchTeamDAO = matchTeamDAO;
        this.matchTeamMapper = matchTeamMapper;
        this.matchPlayerManager = matchPlayerManager;
    }

    @Override
    @Transactional
    public MatchTeamDTO create(MatchTeamDTO matchTeamDTO) {
        MatchTeamDTO createdMatchTeam = super.create(matchTeamDTO);
        createdMatchTeam.setPlayers(matchTeamDTO.getPlayers() == null
            ? new ArrayList<>()
            : matchTeamDTO.getPlayers().stream()
                .map(player -> {
                    player.setMatchId(matchTeamDTO.getMatchId());
                    player.setTournamentId(matchTeamDTO.getTournamentId());
                    return matchPlayerManager.create(player);
                })
                .collect(Collectors.toList())
        );
        return createdMatchTeam;
    }

    @Override
    protected MatchTeamDAO getRwDAO() {
        return matchTeamDAO;
    }

    @Override
    protected MatchTeamMapper getMapper() {
        return matchTeamMapper;
    }
}
