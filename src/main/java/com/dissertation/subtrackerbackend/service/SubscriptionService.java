package com.dissertation.subtrackerbackend.service;

import com.dissertation.subtrackerbackend.domain.Subscription;
import com.dissertation.subtrackerbackend.domain.dto.SubscriptionDTO;

import java.util.List;

public interface SubscriptionService {
    List<SubscriptionDTO> fetchAllSubscriptions();

    SubscriptionDTO fetchSubscriptionById(long id);

    List<SubscriptionDTO> saveMultipleSubscriptions(List<Subscription> subscriptionProviderList);

    SubscriptionDTO saveSubscription(Subscription subscriptionProvider);

    SubscriptionDTO updateSubscription(SubscriptionDTO subscriptionProviderDTO);

//    Subscription softDelete(long id);

    void delete(long id);
}
