package com.junction.idea.service.mapper;

import com.junction.idea.domain.ElevatorPitch;
import com.junction.idea.domain.User;
import com.junction.idea.service.dto.ElevatorPitchDTO;
import com.junction.idea.service.dto.UserDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ElevatorPitch} and its DTO {@link ElevatorPitchDTO}.
 */
@Mapper(componentModel = "spring")
public interface ElevatorPitchMapper extends EntityMapper<ElevatorPitchDTO, ElevatorPitch> {
    @Mapping(target = "inventor", source = "inventor", qualifiedByName = "userId")
    ElevatorPitchDTO toDto(ElevatorPitch s);

    @Named("userId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    UserDTO toDtoUserId(User user);
}
