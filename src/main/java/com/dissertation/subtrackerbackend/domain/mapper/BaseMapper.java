package com.dissertation.subtrackerbackend.domain.mapper;

import org.mapstruct.MapperConfig;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@MapperConfig(componentModel = "spring",
nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BaseMapper<Entity, Dto>{
    Dto toDto(Entity entity);
    List<Dto> toDtos(List<Entity> entities);
    Entity toEntity(Dto dto);
    List<Entity> toEntities(List<Dto> dtos);
}
