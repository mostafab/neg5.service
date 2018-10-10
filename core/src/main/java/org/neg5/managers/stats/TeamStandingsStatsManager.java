package org.neg5.managers.stats;

import com.google.inject.Inject;
import org.neg5.TournamentMatchDTO;
import org.neg5.managers.TournamentMatchManager;

import java.util.ArrayList;
import java.util.List;

public class TeamStandingsStatsManager {

    @Inject private TournamentMatchManager tournamentMatchManager;

    public Object calculate(String tournamentId, String phaseId) {
        List<TournamentMatchDTO> matches = tournamentMatchManager.findAllByTournamentAndPhase(tournamentId, phaseId);
        return new ArrayList<>();
    }
}
