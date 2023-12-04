package com.dissertation.subtrackerbackend.domain.mapper;

import com.dissertation.subtrackerbackend.domain.User;
import com.dissertation.subtrackerbackend.domain.dto.UserDTO;
import org.keycloak.representations.idm.UserRepresentation;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public abstract class UserMapper implements ExtendedMapper<User, UserDTO, UserRepresentation> {

    @Override
    public abstract User toEntity(UserDTO userDTO);

    @Override
    public abstract UserDTO toDto(User user);

    @Override
    public abstract UserRepresentation toRepresentation(UserDTO userDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateUserFromDto(@MappingTarget User user, UserDTO userDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateUserRepresentationFromDto(@MappingTarget UserRepresentation userRepresentation, UserDTO userDTO);


}
