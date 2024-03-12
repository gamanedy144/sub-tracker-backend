package com.dissertation.subtrackerbackend.service.impl;

import com.dissertation.subtrackerbackend.config.JwtService;
import com.dissertation.subtrackerbackend.domain.Subscription;
import com.dissertation.subtrackerbackend.domain.TransactionStatus;
import com.dissertation.subtrackerbackend.domain.Transaction;
import com.dissertation.subtrackerbackend.domain.dto.SubscriptionDTO;
import com.dissertation.subtrackerbackend.domain.mapper.SubscriptionMapper;
import com.dissertation.subtrackerbackend.repository.SubscriptionRepository;
import com.dissertation.subtrackerbackend.repository.TransactionRepository;
import com.dissertation.subtrackerbackend.service.SubscriptionService;
import com.dissertation.subtrackerbackend.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    SubscriptionRepository subscriptionRepository;
    TransactionRepository transactionRepository;
    SubscriptionMapper mapper;
    JwtService jwtService;
    UserService userService;
    private static Logger LOGGER = LoggerFactory.getLogger(SubscriptionServiceImpl.class);
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
            if (subscription.getLastOccurrenceDate() != null && subscription.isActive()){
                processSubscription(subscription, today);
            }
        }
    }

    @Override
    public List<SubscriptionDTO> fetchAllSubscriptions() {
        return subscriptionRepository.findAll().stream().map(subscription -> mapper.toDto(subscription)).collect(Collectors.toList());
    }

    @Override
    public List<SubscriptionDTO> fetchAllSubscriptionsForCurrentUser() {
        return subscriptionRepository.findAllByUserAndActiveTrue(userService.getCurrentUser()).stream()
                .map(subscription -> mapper.toDto(subscription)).collect(Collectors.toList());
    }
    @Override
    public SubscriptionDTO fetchSubscriptionById(long id) {
        return mapper.toDto(subscriptionRepository.findById(id).orElseThrow());
    }

    @Override
    public List<SubscriptionDTO> saveMultipleSubscriptions(List<Subscription> subscriptionList) {
        return subscriptionRepository.saveAll(subscriptionList).stream()
                .map(subscription -> mapper.toDto(subscription))
                .collect(Collectors.toList());
    }

    @Override
    public Subscription saveSubscription(SubscriptionDTO subscriptionDTO) {
        Subscription subscriptionToBeSaved = new Subscription();
        mapper.updateSubscriptionFromDto(subscriptionToBeSaved, subscriptionDTO);
        subscriptionToBeSaved.setUser(userService.getCurrentUser());
    return subscriptionRepository.save(subscriptionToBeSaved);
    }

    @Override
    public Subscription updateSubscription(SubscriptionDTO subscriptionDTO) {
        Subscription subscriptionToBeSaved = subscriptionRepository.findById(subscriptionDTO.getId()).orElseThrow();
        mapper.updateSubscriptionFromDto(subscriptionToBeSaved, subscriptionDTO);
        subscriptionToBeSaved.setUser(userService.getCurrentUser());
        return subscriptionRepository.save(subscriptionToBeSaved);
    }

    @Override
    public Subscription softDelete(long id) {
        Subscription sub = subscriptionRepository.findById(id).orElseThrow();
        sub.setActive(false);
        return subscriptionRepository.save(sub);
    }

    @Override
    public void delete(long id) {
        Subscription temp = subscriptionRepository.findById(id).orElseThrow();
        subscriptionRepository.deleteById(id);
    }
    @Override
    public List<Subscription> getAllSubscriptionsForCurrentUser() {
        return subscriptionRepository.findAllByUserAndActiveTrue(userService.getCurrentUser());
    }

    private void processSubscription(Subscription subscription, LocalDate today) {
        LocalDate lastOccurrenceDate = subscription.getLastOccurrenceDate();
        LocalDate nextOccurrenceDate = subscription.getNextOccurrenceDate();
        while (nextOccurrenceDate.isBefore(today) || nextOccurrenceDate.isEqual(today)) {
            handleTransactionCreation(subscription, lastOccurrenceDate);
            if(Objects.nonNull(subscription.getEndDate()) &&
                    nextOccurrenceDate.isEqual(subscription.getEndDate())) {
                lastOccurrenceDate = nextOccurrenceDate;
                break;
            }
            lastOccurrenceDate = nextOccurrenceDate;
            nextOccurrenceDate = subscription.calculateNextOccurrenceDate(lastOccurrenceDate);

        }

        subscription.setLastOccurrenceDate(lastOccurrenceDate);
        subscription.setNextOccurrenceDate(nextOccurrenceDate);
        subscriptionRepository.save(subscription);
    }

    private void handleTransactionCreation(Subscription subscription, LocalDate date) {
        List<Transaction> timestamps = transactionRepository
                .findAllByTimestampBetweenAndSubscription(
                        date.atStartOfDay(),
                        date.plusDays(1).atStartOfDay().minusNanos(1),
                        subscription
                );

        if (timestamps.isEmpty()) {
            Transaction transaction = Transaction.builder()
                    .subscription(subscription)
                    .timestamp(date.atStartOfDay())
                    .status(TransactionStatus.SUCCESS)
                    .build();
            transactionRepository.save(transaction);
        }
    }

    @Override
    public Integer countSubscriptionsForCurrentUser() {
        return subscriptionRepository.countAllByUserAndActiveTrue(userService.getCurrentUser());
    }
}
