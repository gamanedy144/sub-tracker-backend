package com.dissertation.subtrackerbackend.service.impl;

import com.dissertation.subtrackerbackend.config.JwtService;
import com.dissertation.subtrackerbackend.domain.Subscription;
import com.dissertation.subtrackerbackend.domain.User;
import com.dissertation.subtrackerbackend.domain.dto.SubscriptionDTO;
import com.dissertation.subtrackerbackend.domain.mapper.SubscriptionMapper;
import com.dissertation.subtrackerbackend.repository.SubscriptionRepository;
import com.dissertation.subtrackerbackend.service.SubscriptionService;
import com.dissertation.subtrackerbackend.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    SubscriptionRepository subscriptionRepository;
    SubscriptionMapper mapper;
    JwtService jwtService;
    UserService userService;
    @Override
    public List<SubscriptionDTO> fetchAllSubscriptions() {
        return subscriptionRepository.findAll().stream().map(subscription -> mapper.toDto(subscription)).collect(Collectors.toList());
    }

    @Override
    public List<SubscriptionDTO> fetchAllSubscriptionsForCurrentUser() {
        User currentUser = userService.findByEmail(jwtService.getUsername());
        return subscriptionRepository.findAllByUser(currentUser).stream()
                .map(subscription -> mapper.toDto(subscription)).collect(Collectors.toList());
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
    public Subscription saveSubscription(SubscriptionDTO subscriptionDTO) {
        Subscription subscriptionToBeSaved = new Subscription();
        mapper.updateSubscriptionFromDto(subscriptionToBeSaved, subscriptionDTO);
        subscriptionToBeSaved.setUser(userService.findByEmail(jwtService.getUsername()));
    return subscriptionRepository.save(subscriptionToBeSaved);
    }

    @Override
    public Subscription updateSubscription(SubscriptionDTO subscriptionDTO) {
        Subscription subscriptionToBeSaved = new Subscription();
        mapper.updateSubscriptionFromDto(subscriptionToBeSaved, subscriptionDTO);
        return subscriptionRepository.save(subscriptionToBeSaved);
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

    @Override
    @PostConstruct
    public void initialize() {
        updateSubscriptionDates(); // Run the update method once on startup
    }
    @Override
    @Scheduled(cron = "0 0 0 * * *") // Run daily at midnight
    public void updateSubscriptionDates() {
        List<Subscription> subscriptions = subscriptionRepository.findAll();
        LocalDate today = LocalDate.now();

        for (Subscription subscription : subscriptions) {
            if (subscription.getLastOccurrenceDate() != null &&
                    (subscription.getLastOccurrenceDate().isBefore(today) ||
                            subscription.getLastOccurrenceDate().isEqual(today))) {
                subscription.setLastOccurrenceDate(today);
                subscription.setNextOccurrenceDate(subscription.calculateNextOccurrenceDate(today));
                subscriptionRepository.save(subscription);
            }
        }
    }
}
