package com.dissertation.subtrackerbackend.service;

import com.dissertation.subtrackerbackend.domain.Subscription;
import com.dissertation.subtrackerbackend.domain.dto.SubscriptionDTO;

import java.util.List;

public interface SubscriptionService {
    List<Subscription> fetchAllSubscriptions();

    Subscription fetchSubscriptionById(long id);

    List<Subscription> saveMultipleSubscriptions(List<Subscription> subscriptionProviderList);

    Subscription saveSubscription(Subscription subscriptionProvider);

    Subscription updateSubscription(SubscriptionDTO subscriptionProviderDTO);
}
