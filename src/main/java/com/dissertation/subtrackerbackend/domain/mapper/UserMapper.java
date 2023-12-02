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
    public abstract void updateUserFromDto(UserDTO userDto, @MappingTarget User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateUserRepresentationFromDto(UserDTO userDTO, @MappingTarget UserRepresentation userRepresentation);


}
