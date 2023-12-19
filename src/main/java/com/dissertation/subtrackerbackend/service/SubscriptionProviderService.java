package com.dissertation.subtrackerbackend.service;

import com.dissertation.subtrackerbackend.domain.SubscriptionProvider;
import com.dissertation.subtrackerbackend.domain.dto.SubscriptionProviderDTO;

import java.util.List;

public interface SubscriptionProviderService {
    List<SubscriptionProviderDTO> fetchAllSubscriptionProviders();

    SubscriptionProviderDTO fetchSubscriptionProviderById(long id);

    List<SubscriptionProviderDTO> saveMultipleSubscriptionProviders(List<SubscriptionProvider> subscriptionProviderList);

    SubscriptionProviderDTO saveSubscriptionProvider(SubscriptionProvider subscriptionProvider);

    SubscriptionProviderDTO updateSubscriptionProvider(SubscriptionProviderDTO subscriptionProviderDTO);

    void delete(long id);

    SubscriptionProviderDTO updateSubscriptionProviderById(long id, SubscriptionProviderDTO subscriptionProviderDTO);
}
