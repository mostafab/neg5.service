package org.neg5.managers.stats;

import com.google.inject.Inject;
import org.neg5.MatchPlayerAnswerDTO;
import org.neg5.TournamentMatchDTO;
import org.neg5.TournamentTossupValueDTO;
import org.neg5.enums.TossupAnswerType;
import org.neg5.managers.TournamentManager;
import org.neg5.managers.TournamentMatchManager;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TeamStandingsStatsManager {

    @Inject private TournamentMatchManager tournamentMatchManager;
    @Inject private TournamentManager tournamentManager;

    public Object calculate(String tournamentId, String phaseId) {
        List<TournamentMatchDTO> matches = tournamentMatchManager.findAllByTournamentAndPhase(tournamentId, phaseId);
        Map<Integer, TossupAnswerType> tossupValues = tournamentManager.get(tournamentId).getTossupValues().stream()
                .collect(Collectors.toMap(TournamentTossupValueDTO::getValue, TournamentTossupValueDTO::getAnswerType));

        return matches.stream()
                .flatMap(match -> match.getTeams().stream())
                .flatMap(team -> team.getPlayers().stream())
                .flatMap(player -> player.getAnswers().stream())
                .filter(answer -> answer.getNumberGotten() > 0)
                .map(MatchPlayerAnswerDTO::getTossupValue)
                .map(tossupValues::get)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}
