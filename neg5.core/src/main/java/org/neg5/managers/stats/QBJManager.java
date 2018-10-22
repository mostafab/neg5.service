package org.neg5.managers.stats;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.neg5.TournamentDTO;
import org.neg5.enums.TossupAnswerType;
import org.neg5.managers.TournamentManager;
import org.neg5.managers.TournamentMatchManager;
import org.neg5.managers.TournamentTeamManager;
import org.neg5.qbj.AnswerTypeDTO;
import org.neg5.qbj.ScoringRulesDTO;
import org.neg5.qbj.TournamentQbjDTO;
import org.neg5.qbj.TournamentSiteDTO;

import java.util.stream.Collectors;

@Singleton
public class QBJManager {

    @Inject private TournamentManager tournamentManager;
    @Inject private TournamentMatchManager tournamentMatchManager;
    @Inject private TournamentTeamManager teamManager;

    public TournamentQbjDTO getQbj(String tournamentId) {
        TournamentDTO tournament = tournamentManager.get(tournamentId);

        TournamentQbjDTO qbj = new TournamentQbjDTO();
        qbj.setName(tournament.getName());
        qbj.setQuestionSet(tournament.getQuestionSet());
        qbj.setTournamentSite(getSite(tournament));
        qbj.setScoringRules(getScoringRules(tournament));

        return qbj;
    }

    private TournamentSiteDTO getSite(TournamentDTO tournament) {
        TournamentSiteDTO site = new TournamentSiteDTO();
        site.setName(tournament.getLocation());

        return site;
    }

    private ScoringRulesDTO getScoringRules(TournamentDTO tournament) {
        ScoringRulesDTO rules = new ScoringRulesDTO();
        rules.setBonusesBounceBack(tournament.getUsesBouncebacks());

        rules.setAnswerTypes(
                tournament.getTossupValues().stream()
                        .map(tv -> {
                            AnswerTypeDTO answerType = new AnswerTypeDTO();
                            answerType.setValue(tv.getValue());
                            answerType.setAwardsBonus(TossupAnswerType.NEG != tv.getAnswerType() && tv.getAnswerType() != null);
                            return answerType;
                        })
                        .collect(Collectors.toList())
        );

        return rules;
    }
}
