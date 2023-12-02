package com.dissertation.subtrackerbackend.domain.mapper;

public interface ExtendedMapper<Entity, Dto, Representation> extends BaseMapper<Entity, Dto>{


    Representation toRepresentation(Dto dto);
}
