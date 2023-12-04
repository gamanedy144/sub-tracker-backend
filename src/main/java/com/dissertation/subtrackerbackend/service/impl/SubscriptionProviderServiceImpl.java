package com.dissertation.subtrackerbackend.service.impl;

import com.dissertation.subtrackerbackend.domain.SubscriptionProvider;
import com.dissertation.subtrackerbackend.domain.dto.SubscriptionProviderDTO;
import com.dissertation.subtrackerbackend.domain.mapper.SubscriptionProviderMapper;
import com.dissertation.subtrackerbackend.repository.SubscriptionProviderRepository;
import com.dissertation.subtrackerbackend.service.SubscriptionProviderService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
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

    @Override
    public void delete(long id) {
        SubscriptionProvider temp = subscriptionProviderRepository.findById(id).orElseThrow();
        subscriptionProviderRepository.deleteById(id);
    }

    @Override
    public SubscriptionProvider updateSubscriptionProviderById(long id, SubscriptionProviderDTO subscriptionProviderDTO) {
        SubscriptionProvider subscriptionProviderToBeSaved = subscriptionProviderRepository.findById(id).orElseThrow();
        mapper.updateSubscriptionProviderFromDto(subscriptionProviderToBeSaved, subscriptionProviderDTO);
        return subscriptionProviderRepository.save(subscriptionProviderToBeSaved);
    }
}
