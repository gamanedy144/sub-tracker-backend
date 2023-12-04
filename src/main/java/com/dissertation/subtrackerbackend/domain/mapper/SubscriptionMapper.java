package com.dissertation.subtrackerbackend.domain.mapper;

import com.dissertation.subtrackerbackend.domain.Subscription;
import com.dissertation.subtrackerbackend.domain.dto.SubscriptionDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

public abstract class SubscriptionMapper implements BaseMapper<Subscription, SubscriptionDTO> {

    @Override
    public abstract Subscription toEntity(SubscriptionDTO subscriptionDTO);

    @Override
    public abstract SubscriptionDTO toDto(Subscription subscription);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateSubscriptionProviderFromDto(@MappingTarget Subscription subscription, SubscriptionDTO subscriptionDTO);


}
