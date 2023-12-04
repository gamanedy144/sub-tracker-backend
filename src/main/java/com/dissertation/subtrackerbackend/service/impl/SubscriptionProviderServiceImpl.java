package com.dissertation.subtrackerbackend.service.impl;

import com.dissertation.subtrackerbackend.domain.SubscriptionProvider;
import com.dissertation.subtrackerbackend.domain.dto.SubscriptionProviderDTO;
import com.dissertation.subtrackerbackend.domain.mapper.SubscriptionProviderMapper;
import com.dissertation.subtrackerbackend.repository.SubscriptionProviderRepository;
import com.dissertation.subtrackerbackend.service.SubscriptionProviderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionProviderServiceImpl implements SubscriptionProviderService {

    SubscriptionProviderRepository subscriptionProviderRepository;
    SubscriptionProviderMapper mapper;
    @Override
    public List<SubscriptionProvider> fetchAllSubscriptionProviders() {
        return subscriptionProviderRepository.findAll();
    }

    @Override
    public SubscriptionProvider fetchSubscriptionProviderById(long id) {
        return subscriptionProviderRepository.findById(id).orElseThrow();
    }

    @Override
    public List<SubscriptionProvider> saveMultipleSubscriptionProviders(List<SubscriptionProvider> subscriptionProviderList) {
        return subscriptionProviderRepository.saveAll(subscriptionProviderList);
    }

    @Override
    public SubscriptionProvider saveSubscriptionProvider(SubscriptionProvider subscriptionProvider) {
        return subscriptionProviderRepository.save(subscriptionProvider);
    }

    @Override
    public SubscriptionProvider updateSubscriptionProvider(SubscriptionProviderDTO subscriptionProviderDTO) {
        SubscriptionProvider subscriptionProviderToBeSaved = new SubscriptionProvider();
        mapper.updateSubscriptionProviderFromDto(subscriptionProviderToBeSaved, subscriptionProviderDTO);
        return subscriptionProviderRepository.save(subscriptionProviderToBeSaved);
    }
}
