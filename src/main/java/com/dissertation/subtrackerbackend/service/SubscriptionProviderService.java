package com.dissertation.subtrackerbackend.service;

import com.dissertation.subtrackerbackend.domain.SubscriptionProvider;
import com.dissertation.subtrackerbackend.domain.dto.SubscriptionProviderDTO;

import java.util.List;

public interface SubscriptionProviderService {
    List<SubscriptionProvider> fetchAllSubscriptionProviders();

    SubscriptionProvider fetchSubscriptionProviderById(long id);

    List<SubscriptionProvider> saveMultipleSubscriptionProviders(List<SubscriptionProvider> subscriptionProviderList);

    SubscriptionProvider saveSubscriptionProvider(SubscriptionProvider subscriptionProvider);

    SubscriptionProvider updateSubscriptionProvider(SubscriptionProviderDTO subscriptionProviderDTO);
}
