package com.junction.idea.service.mapper;

import com.junction.idea.domain.ElevatorPitch;
import com.junction.idea.service.dto.ElevatorPitchDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ElevatorPitch} and its DTO {@link ElevatorPitchDTO}.
 */
@Mapper(componentModel = "spring")
public interface ElevatorPitchMapper extends EntityMapper<ElevatorPitchDTO, ElevatorPitch> {}
