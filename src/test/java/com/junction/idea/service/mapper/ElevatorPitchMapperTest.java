package com.junction.idea.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ElevatorPitchMapperTest {

    private ElevatorPitchMapper elevatorPitchMapper;

    @BeforeEach
    public void setUp() {
        elevatorPitchMapper = new ElevatorPitchMapperImpl();
    }
}
