package com.dissertation.subtrackerbackend.domain.mapper;

import com.dissertation.subtrackerbackend.domain.SubscriptionProvider;
import com.dissertation.subtrackerbackend.domain.dto.SubscriptionProviderDTO;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public abstract class SubscriptionProviderMapper implements BaseMapper<SubscriptionProvider, SubscriptionProviderDTO> {

    @Override
    public abstract SubscriptionProvider toEntity(SubscriptionProviderDTO userDTO);

    @Override
    public abstract SubscriptionProviderDTO toDto(SubscriptionProvider user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateSubscriptionProviderFromDto(@MappingTarget SubscriptionProvider subscriptionProvider, SubscriptionProviderDTO subscriptionProviderDTO);


}
