package com.junction.idea.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.junction.idea.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ElevatorPitchDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ElevatorPitchDTO.class);
        ElevatorPitchDTO elevatorPitchDTO1 = new ElevatorPitchDTO();
        elevatorPitchDTO1.setId(1L);
        ElevatorPitchDTO elevatorPitchDTO2 = new ElevatorPitchDTO();
        assertThat(elevatorPitchDTO1).isNotEqualTo(elevatorPitchDTO2);
        elevatorPitchDTO2.setId(elevatorPitchDTO1.getId());
        assertThat(elevatorPitchDTO1).isEqualTo(elevatorPitchDTO2);
        elevatorPitchDTO2.setId(2L);
        assertThat(elevatorPitchDTO1).isNotEqualTo(elevatorPitchDTO2);
        elevatorPitchDTO1.setId(null);
        assertThat(elevatorPitchDTO1).isNotEqualTo(elevatorPitchDTO2);
    }
}
