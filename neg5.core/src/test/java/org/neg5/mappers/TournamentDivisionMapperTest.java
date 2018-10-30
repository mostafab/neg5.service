package org.neg5.mappers;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.neg5.TournamentDivisionDTO;
import org.neg5.data.Tournament;
import org.neg5.data.TournamentDivision;
import org.neg5.data.TournamentPhase;

@ExtendWith(MockitoExtension.class)
public class TournamentDivisionMapperTest {

    @InjectMocks private TournamentDivisionMapper divisionMapper;

    private static final String TOURNAMENT_ID = "TEST_ID";
    private static final String PHASE_ID = "PHASE_ID";

    @Test
    public void testEntityToDTOMapping() {
        TournamentDivision entity = buildEntity();

        TournamentDivisionDTO dto = divisionMapper.toDTO(entity);

        Assert.assertEquals(entity.getId(), dto.getId());
        Assert.assertEquals(entity.getName(), dto.getName());

        Assert.assertEquals(entity.getPhase().getId(), dto.getPhaseId());
        Assert.assertEquals(entity.getTournament().getId(), dto.getTournamentId());
    }

    @Test
    public void testEntityToDtoMappingNullTournamentAndPhase() {
        TournamentDivision entity = buildEntity();
        entity.setPhase(null);
        entity.setTournament(null);

        TournamentDivisionDTO dto = divisionMapper.toDTO(entity);

        Assert.assertNull(dto.getPhaseId());
        Assert.assertNull(dto.getTournamentId());
    }

    @Test
    public void testDtoToEntityMapping() {
        TournamentDivisionDTO dto = buildDTO();

        TournamentDivision entity = divisionMapper.mergeToEntity(dto);

        Assert.assertEquals(dto.getId(), entity.getId());
        Assert.assertEquals(dto.getName(), entity.getName());

        Assert.assertNotNull(entity.getTournament());
        Assert.assertNotNull(entity.getPhase());

        Assert.assertEquals(dto.getPhaseId(), entity.getPhase().getId());
        Assert.assertEquals(dto.getTournamentId(), entity.getTournament().getId());
    }

    @Test
    public void testDtoToEntityMappingNoTournamentOrPhaseId() {
        TournamentDivisionDTO dto = buildDTO();
        dto.setPhaseId(null);
        dto.setTournamentId(null);

        TournamentDivision entity = divisionMapper.mergeToEntity(dto);

        Assert.assertNull(entity.getPhase());
        Assert.assertNull(entity.getTournament());
    }

    private TournamentDivision buildEntity() {
        TournamentDivision division = new TournamentDivision();

        division.setId("TEST_ID_DIV");
        division.setName("PHASE");

        division.setTournament(new Tournament());
        division.getTournament().setId(TOURNAMENT_ID);

        division.setPhase(new TournamentPhase());
        division.getPhase().setId(PHASE_ID);

        return division;
    }

    private TournamentDivisionDTO buildDTO() {
        TournamentDivisionDTO division = new TournamentDivisionDTO();

        division.setId("TEST_ID_DIV");
        division.setName("PHASE");

        division.setTournamentId(TOURNAMENT_ID);
        division.setPhaseId(PHASE_ID);

        return division;
    }
}
