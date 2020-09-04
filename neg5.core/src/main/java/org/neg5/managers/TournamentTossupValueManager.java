package org.neg5.managers;

import com.google.common.collect.Sets;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.neg5.TournamentTossupValueDTO;
import org.neg5.daos.TournamentTossupValueDAO;
import org.neg5.data.TournamentTossupValue;
import org.neg5.data.embeddables.TournamentTossupValueId;
import org.neg5.enums.TossupAnswerType;
import org.neg5.mappers.TournamentTossupValueMapper;

import java.util.Set;

@Singleton
public class TournamentTossupValueManager extends
        AbstractDTOManager<TournamentTossupValue, TournamentTossupValueDTO, TournamentTossupValueId> {

    @Inject private TournamentTossupValueDAO rwTournamentTossupValueDAO;

    @Inject private TournamentTossupValueMapper tournamentTossupValueMapper;

    public Set<TournamentTossupValueDTO> getDefaultTournamentValues() {
        return Sets.newHashSet(
                getStub(-5, TossupAnswerType.NEG),
                getStub(10, TossupAnswerType.BASE),
                getStub(15, TossupAnswerType.POWER)
        );
    }

    @Override
    protected TournamentTossupValueDAO getRwDAO() {
        return rwTournamentTossupValueDAO;
    }

    @Override
    protected TournamentTossupValueMapper getMapper() {
        return tournamentTossupValueMapper;
    }

    @Override
    protected TournamentTossupValueId getIdFromDTO(TournamentTossupValueDTO tournamentTossupValueDTO) {
        return getMapper().mergeToEntity(tournamentTossupValueDTO).getId();
    }

    private TournamentTossupValueDTO getStub(int points, TossupAnswerType answerType) {
        TournamentTossupValueDTO dto = new TournamentTossupValueDTO();
        dto.setAnswerType(answerType);
        dto.setValue(points);
        return dto;
    }
}
