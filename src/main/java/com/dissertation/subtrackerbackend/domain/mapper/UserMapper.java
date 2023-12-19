package com.dissertation.subtrackerbackend.domain.mapper;
import com.dissertation.subtrackerbackend.domain.User;
import com.dissertation.subtrackerbackend.domain.dto.UserDTO;
import org.mapstruct.*;


@Mapper(
        componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {SubscriptionMapper.class}
)
public interface UserMapper extends BaseMapper<User, UserDTO> {

    @Override
    public abstract User toEntity(UserDTO userDTO);

    @Override
    public abstract UserDTO toDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateUserFromDto(@MappingTarget User user, UserDTO userDTO);

}