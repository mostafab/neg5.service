package org.neg5.managers.stats;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.neg5.TournamentDTO;
import org.neg5.TournamentTeamDTO;
import org.neg5.enums.TossupAnswerType;
import org.neg5.managers.QBJUtil;
import org.neg5.managers.TournamentManager;
import org.neg5.managers.TournamentMatchManager;
import org.neg5.managers.TournamentTeamManager;
import org.neg5.qbj.AnswerTypeDTO;
import org.neg5.qbj.RegistrationDTO;
import org.neg5.qbj.ScoringRulesDTO;
import org.neg5.qbj.TournamentQbjDTO;
import org.neg5.qbj.TournamentSiteDTO;

import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class QBJManager {

    private final TournamentManager tournamentManager;
    private final TournamentTeamManager teamManager;

    @Inject
    public QBJManager(TournamentManager tournamentManager,
                      TournamentTeamManager teamManager) {
        this.tournamentManager = tournamentManager;
        this.teamManager = teamManager;
    }

    public TournamentQbjDTO getQbj(String tournamentId) {
        TournamentDTO tournament = tournamentManager.get(tournamentId);

        TournamentQbjDTO qbj = new TournamentQbjDTO();
        qbj.setName(tournament.getName());
        qbj.setQuestionSet(tournament.getQuestionSet());
        qbj.setTournamentSite(getSite(tournament));
        qbj.setScoringRules(getScoringRules(tournament));

        qbj.setRegistrations(getRegistrations(tournamentId));

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
        rules.setMinimumPartsPerBonus(tournament.getPartsPerBonus());
        rules.setPointsPerBonusPart(tournament.getBonusPointValue());

        if (tournament.getPartsPerBonus() != null
                && tournament.getBonusPointValue() != null) {
            rules.setMaximumBonusScore(tournament.getPartsPerBonus() * tournament.getBonusPointValue());
        }

        rules.setAnswerTypes(
                tournament.getTossupValues().stream()
                        .map(tv -> {
                            AnswerTypeDTO answerType = new AnswerTypeDTO();
                            answerType.setValue(tv.getValue());
                            answerType.setAwardsBonus(TossupAnswerType.NEG != tv.getAnswerType()
                                    && tv.getAnswerType() != null);
                            return answerType;
                        })
                        .collect(Collectors.toList())
        );

        return rules;
    }

    private List<RegistrationDTO> getRegistrations(String tournamentId) {
        List<TournamentTeamDTO> teams = teamManager.findAllByTournamentId(tournamentId);
        return QBJUtil.toRegistrations(teams);
    }
}
