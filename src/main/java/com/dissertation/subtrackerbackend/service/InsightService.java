package com.dissertation.subtrackerbackend.service;

import com.dissertation.subtrackerbackend.domain.SubscriptionCategory;
import com.dissertation.subtrackerbackend.domain.dto.SubscriptionDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface InsightService {
    Map<String, Double> calculateMonthlySpendings();

    Map<String, Map<SubscriptionCategory, Double>> calculateMonthlySpendingsByCategory();

    Map<String, Double> calculateTotalSpendingPerMonthOrYear(String period);

    Map<String, Double> estimateFutureSpending(int months);

    double calculateTotalSpendingForSubscriptions(List<SubscriptionDTO> subscriptions, LocalDate date);

    double calculateSubscriptionSpending(SubscriptionDTO subscriptionDTO, LocalDate date);
}
