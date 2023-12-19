package com.dissertation.subtrackerbackend.service.impl;

import com.dissertation.subtrackerbackend.domain.Subscription;
import com.dissertation.subtrackerbackend.domain.dto.SubscriptionDTO;
import com.dissertation.subtrackerbackend.domain.mapper.SubscriptionMapper;
import com.dissertation.subtrackerbackend.repository.SubscriptionRepository;
import com.dissertation.subtrackerbackend.service.SubscriptionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    SubscriptionRepository subscriptionRepository;
    SubscriptionMapper mapper;
    @Override
    public List<SubscriptionDTO> fetchAllSubscriptions() {
        return subscriptionRepository.findAll().stream().map(subscription -> mapper.toDto(subscription)).collect(Collectors.toList());
    }

    @Override
    public SubscriptionDTO fetchSubscriptionById(long id) {
        return mapper.toDto(subscriptionRepository.findById(id).orElseThrow());
    }

    @Override
    public List<SubscriptionDTO> saveMultipleSubscriptions(List<Subscription> subscriptionList) {
        return subscriptionRepository.saveAll(subscriptionList).stream().map(subscription -> mapper.toDto(subscription)).collect(Collectors.toList());
    }

    @Override
    public SubscriptionDTO saveSubscription(Subscription subscription) {
        return mapper.toDto(subscriptionRepository.save(subscription));
    }

    @Override
    public SubscriptionDTO updateSubscription(SubscriptionDTO subscriptionDTO) {
        Subscription subscriptionToBeSaved = new Subscription();
        mapper.updateSubscriptionProviderFromDto(subscriptionToBeSaved, subscriptionDTO);
        return mapper.toDto(subscriptionRepository.save(subscriptionToBeSaved));
    }

//    @Override
//    public Subscription softDelete(long id) {
//        Subscription sub = subscriptionRepository.findById(id).orElseThrow();
//        sub.setActive(false);
//        return subscriptionRepository.save(sub);
//    }

    @Override
    public void delete(long id) {
        Subscription temp = subscriptionRepository.findById(id).orElseThrow();
        subscriptionRepository.deleteById(id);
    }
}
