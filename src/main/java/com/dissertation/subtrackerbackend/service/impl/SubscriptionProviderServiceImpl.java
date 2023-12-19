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
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class SubscriptionProviderServiceImpl implements SubscriptionProviderService {

    SubscriptionProviderRepository subscriptionProviderRepository;
    SubscriptionProviderMapper mapper;
    @Override
    public List<SubscriptionProviderDTO> fetchAllSubscriptionProviders() {
        return subscriptionProviderRepository.findAll().stream().map(subscriptionProvider -> mapper.toDto(subscriptionProvider)).collect(Collectors.toList());
    }

    @Override
    public SubscriptionProviderDTO fetchSubscriptionProviderById(long id) {
        return mapper.toDto(subscriptionProviderRepository.findById(id).orElseThrow());
    }

    @Override
    public List<SubscriptionProviderDTO> saveMultipleSubscriptionProviders(List<SubscriptionProvider> subscriptionProviderList) {
        return subscriptionProviderRepository.saveAll(subscriptionProviderList).stream().map(subscriptionProvider -> mapper.toDto(subscriptionProvider)).collect(Collectors.toList());
    }

    @Override
    public SubscriptionProviderDTO saveSubscriptionProvider(SubscriptionProvider subscriptionProvider) {
        return mapper.toDto(subscriptionProviderRepository.save(subscriptionProvider));
    }

    @Override
    public SubscriptionProviderDTO updateSubscriptionProvider(SubscriptionProviderDTO subscriptionProviderDTO) {
        SubscriptionProvider subscriptionProviderToBeSaved = new SubscriptionProvider();
        mapper.updateSubscriptionProviderFromDto(subscriptionProviderToBeSaved, subscriptionProviderDTO);
        return mapper.toDto(subscriptionProviderRepository.save(subscriptionProviderToBeSaved));
    }

    @Override
    public void delete(long id) {
        SubscriptionProvider temp = subscriptionProviderRepository.findById(id).orElseThrow();
        subscriptionProviderRepository.deleteById(id);
    }

    @Override
    public SubscriptionProviderDTO updateSubscriptionProviderById(long id, SubscriptionProviderDTO subscriptionProviderDTO) {
        SubscriptionProvider subscriptionProviderToBeSaved = subscriptionProviderRepository.findById(id).orElseThrow();
        mapper.updateSubscriptionProviderFromDto(subscriptionProviderToBeSaved, subscriptionProviderDTO);
        return mapper.toDto(subscriptionProviderRepository.save(subscriptionProviderToBeSaved));
    }
}
