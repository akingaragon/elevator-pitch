package com.junction.idea.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.junction.idea.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ElevatorPitchTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ElevatorPitch.class);
        ElevatorPitch elevatorPitch1 = new ElevatorPitch();
        elevatorPitch1.setId(1L);
        ElevatorPitch elevatorPitch2 = new ElevatorPitch();
        elevatorPitch2.setId(elevatorPitch1.getId());
        assertThat(elevatorPitch1).isEqualTo(elevatorPitch2);
        elevatorPitch2.setId(2L);
        assertThat(elevatorPitch1).isNotEqualTo(elevatorPitch2);
        elevatorPitch1.setId(null);
        assertThat(elevatorPitch1).isNotEqualTo(elevatorPitch2);
    }
}
