package com.dissertation.subtrackerbackend.service.impl;

import com.dissertation.subtrackerbackend.domain.Subscription;
import com.dissertation.subtrackerbackend.domain.dto.SubscriptionDTO;
import com.dissertation.subtrackerbackend.domain.mapper.SubscriptionMapper;
import com.dissertation.subtrackerbackend.repository.SubscriptionRepository;
import com.dissertation.subtrackerbackend.service.SubscriptionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    SubscriptionRepository subscriptionRepository;
    SubscriptionMapper mapper;
    @Override
    public List<Subscription> fetchAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    @Override
    public Subscription fetchSubscriptionById(long id) {
        return subscriptionRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Subscription> saveMultipleSubscriptions(List<Subscription> subscriptionList) {
        return subscriptionRepository.saveAll(subscriptionList);
    }

    @Override
    public Subscription saveSubscription(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription updateSubscription(SubscriptionDTO subscriptionDTO) {
        Subscription subscriptionToBeSaved = new Subscription();
        mapper.updateSubscriptionProviderFromDto(subscriptionToBeSaved, subscriptionDTO);
        return subscriptionRepository.save(subscriptionToBeSaved);
    }
}
