package org.neg5.mappers;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.neg5.TournamentPhaseDTO;
import org.neg5.data.Tournament;
import org.neg5.data.TournamentPhase;

@ExtendWith(MockitoExtension.class)
public class TournamentPhaseMapperTest {

    @InjectMocks
    private TournamentPhaseMapper phaseMapper;

    @Test
    public void testMappingEntityToDto() {
        TournamentPhase phase = buildEntity();

        TournamentPhaseDTO dto = phaseMapper.toDTO(phase);

        Assert.assertEquals(phase.getId(), dto.getId());
        Assert.assertEquals(phase.getName(), dto.getName());
        Assert.assertEquals(phase.getTournament().getId(), dto.getTournamentId());
    }

    @Test
    public void testMappingEntityToDtoNoTournament() {
        TournamentPhase phase = buildEntity();
        phase.setTournament(null);

        TournamentPhaseDTO dto = phaseMapper.toDTO(phase);

        Assert.assertNull(dto.getTournamentId());
    }

    @Test
    public void testMappingDtoToEntity() {
        TournamentPhaseDTO dto = buildDTO();

        TournamentPhase entity = phaseMapper.mergeToEntity(dto);

        Assert.assertEquals(dto.getId(), entity.getId());
        Assert.assertEquals(dto.getName(), entity.getName());

        Assert.assertNotNull(entity.getTournament());
        Assert.assertEquals(dto.getTournamentId(), entity.getTournament().getId());
    }

    @Test
    public void testMappingDtoToEntityNoTournament() {
        TournamentPhaseDTO dto = buildDTO();
        dto.setTournamentId(null);

        TournamentPhase entity = phaseMapper.mergeToEntity(dto);
        Assert.assertNull(entity.getTournament());
    }

    private TournamentPhase buildEntity() {
        TournamentPhase entity = new TournamentPhase();
        entity.setId("12345");
        entity.setName("PHASE_NAME");

        entity.setTournament(new Tournament());
        entity.getTournament().setId("ID");

        return entity;
    }

    private TournamentPhaseDTO buildDTO() {
        TournamentPhaseDTO dto = new TournamentPhaseDTO();
        dto.setId("12345");
        dto.setName("PHASE_NAME");

        dto.setTournamentId("TEST_ID");

        return dto;
    }
}
