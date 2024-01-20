package com.dissertation.subtrackerbackend.service;

import com.dissertation.subtrackerbackend.domain.Subscription;
import com.dissertation.subtrackerbackend.domain.dto.SubscriptionDTO;
import jakarta.annotation.PostConstruct;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

public interface SubscriptionService {
    List<SubscriptionDTO> fetchAllSubscriptions();
    List<SubscriptionDTO> fetchAllSubscriptionsForCurrentUser();

    SubscriptionDTO fetchSubscriptionById(long id);

    List<SubscriptionDTO> saveMultipleSubscriptions(List<Subscription> subscriptionProviderList);

    Subscription saveSubscription(SubscriptionDTO subscriptionProvider);

    Subscription updateSubscription(SubscriptionDTO subscriptionProviderDTO);

//    Subscription softDelete(long id);

    void delete(long id);

    @PostConstruct
    void initialize();

    @Scheduled(cron = "0 0 0 * * *") // Run daily at midnight
    void updateSubscriptionDates();

    List<SubscriptionDTO> getAllSubscriptionsForCurrentUser();
}
