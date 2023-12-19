package com.dissertation.subtrackerbackend.domain.mapper;

import com.dissertation.subtrackerbackend.domain.Subscription;
import com.dissertation.subtrackerbackend.domain.dto.SubscriptionDTO;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR
//        uses = {UserMapper.class, SubscriptionProviderMapper.class}
)
public interface SubscriptionMapper extends BaseMapper<Subscription, SubscriptionDTO> {


    @Override
    public abstract Subscription toEntity(SubscriptionDTO subscriptionDTO);

    @Override
    @Mapping(target = "user", ignore = true)
    public abstract SubscriptionDTO toDto(Subscription subscription);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateSubscriptionProviderFromDto(@MappingTarget Subscription subscription, SubscriptionDTO subscriptionDTO);

}
