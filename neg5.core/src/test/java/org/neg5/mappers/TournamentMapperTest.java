package org.neg5.mappers;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.neg5.TournamentDTO;
import org.neg5.data.Tournament;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class TournamentMapperTest {

    @Mock private TournamentPhaseMapper phaseMapper;
    @Mock private TournamentDivisionMapper divisionMapper;
    @Mock private TournamentTossupValueMapper tournamentTossupValueMapper;

    @InjectMocks private TournamentMapper tournamentMapper;

    private static final String TOURNAMENT_ID = "THIS_ID";

    @Test
    public void testMapsOwnPropertiesCorrectlyFromEntityToDTO() {
        Tournament tournament = buildTournament();

        TournamentDTO dto = tournamentMapper.toDTO(tournament);

        Assert.assertEquals(tournament.getId(), dto.getId());
        Assert.assertEquals(tournament.getName(), dto.getName());
        Assert.assertEquals(tournament.getBonusPointValue(), dto.getBonusPointValue());
    }

    @Test
    public void testDoesNotCallSubMappersIfNullFromEntityToDTO() {
        Tournament tournament = buildTournament();
        tournament.setPhases(null);
        tournament.setDivisions(null);
        tournament.setTossupValues(null);

        tournamentMapper.toDTO(tournament);
        Mockito.verify(phaseMapper, Mockito.never()).toDTO(any());
        Mockito.verify(divisionMapper, Mockito.never()).toDTO(any());
        Mockito.verify(tournamentTossupValueMapper, Mockito.never()).toDTO(any());
    }

    @Test
    public void testMergeToEntityMapsPropertiesCorrectly() {
        TournamentDTO tournament = buildTournamentDTO();
        Tournament entity = tournamentMapper.mergeToEntity(tournament);

        Assert.assertEquals(tournament.getId(), entity.getId());
        Assert.assertEquals(tournament.getName(), entity.getName());
        Assert.assertEquals(tournament.getBonusPointValue(), entity.getBonusPointValue());
    }

    @Test
    public void testMergeToEntityPhaseIdMapped() {
        TournamentDTO tournament = buildTournamentDTO();

        String phaseId = "PHASE";

        tournament.setCurrentPhaseId(phaseId);

        Tournament entity = tournamentMapper.mergeToEntity(tournament);
        Assert.assertNotNull(entity.getCurrentPhase());
        Assert.assertEquals(tournament.getCurrentPhaseId(), entity.getCurrentPhase().getId());
    }

    private Tournament buildTournament() {
        Tournament tournament = new Tournament();
        tournament.setId(TOURNAMENT_ID);
        tournament.setName("NAME");
        tournament.setBonusPointValue(10L);

        return tournament;
    }

    private TournamentDTO buildTournamentDTO() {
        TournamentDTO tournament = new TournamentDTO();
        tournament.setId(TOURNAMENT_ID);
        tournament.setName("NAME");
        tournament.setBonusPointValue(10L);

        return tournament;
    }
}
