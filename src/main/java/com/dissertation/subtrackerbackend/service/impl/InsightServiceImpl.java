package com.dissertation.subtrackerbackend.service.impl;

import com.dissertation.subtrackerbackend.config.JwtService;
import com.dissertation.subtrackerbackend.domain.Subscription;
import com.dissertation.subtrackerbackend.domain.SubscriptionCategory;
import com.dissertation.subtrackerbackend.domain.dto.SubscriptionDTO;
import com.dissertation.subtrackerbackend.domain.dto.TransactionDTO;
import com.dissertation.subtrackerbackend.domain.mapper.SubscriptionMapper;
import com.dissertation.subtrackerbackend.domain.mapper.TransactionMapper;
import com.dissertation.subtrackerbackend.service.InsightService;
import com.dissertation.subtrackerbackend.service.SubscriptionService;
import com.dissertation.subtrackerbackend.service.TransactionService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Service
public class InsightServiceImpl implements InsightService {

    TransactionService transactionService;
    JwtService jwtService;
    SubscriptionService subscriptionService;
    SubscriptionMapper subscriptionMapper;
    TransactionMapper transactionMapper;

    @Override
    public Map<String, Double> calculateMonthlySpendings() {
        List<TransactionDTO> transactions = transactionService.findAllDtosForCurrentUser();

        return transactions.stream()
                .collect(Collectors.groupingBy(
                        transaction -> transaction.getTimestamp().format(DateTimeFormatter.ofPattern("yyyy-MM")),
                        Collectors.summingDouble(transaction -> transaction.getSubscription().getPrice())
                ));
    }
    @Override
    public Map<String, Map<SubscriptionCategory, Double>> calculateMonthlySpendingsByCategory() {
        List<TransactionDTO> transactionList = transactionService.findAllDtosForCurrentUser();

        return transactionList.stream()
                .collect(Collectors.groupingBy(
                        transaction -> transaction.getTimestamp().format(DateTimeFormatter.ofPattern("yyyy-MM")),
                        Collectors.groupingBy(
                                transaction -> transaction.getSubscription().getCategory(),
                                Collectors.summingDouble(transaction -> transaction.getSubscription().getPrice())
                        )
                ));
    }

    @Override
    public Map<String, Double> calculateTotalSpendingPerMonthOrYear(String period) {
        List<SubscriptionDTO> activeSubscriptions = subscriptionService
                .fetchAllSubscriptionsForCurrentUser();

        List<TransactionDTO> transactions = transactionService
                .findBySubscriptions(subscriptionMapper.toEntities(activeSubscriptions));

        return transactions.stream()
                .collect(Collectors.groupingBy(
                        transaction -> transaction.getTimestamp().format(DateTimeFormatter.ofPattern(period)),
                        Collectors.summingDouble(transaction -> transaction.getSubscription().getPrice())
                ));
    }
    @Override
    public Map<String, Double> estimateFutureSpending(int months) {
        // Fetch active subscriptions for the user
        List<SubscriptionDTO> activeSubscriptions = subscriptionService.fetchAllSubscriptionsForCurrentUser();

        Map<String, Double> futureSpending = new LinkedHashMap<>();

        // Calculate spending projections for the next 'months' months
        for (int i = 0; i < months; i++) {
            LocalDate currentDate = LocalDate.now().plusMonths(i);
            double totalSpendingForMonth = calculateTotalSpendingForSubscriptions(activeSubscriptions, currentDate);
            futureSpending.put(currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM")), totalSpendingForMonth);
        }

        return futureSpending;
    }

    @Override
    public double calculateTotalSpendingForSubscriptions(List<SubscriptionDTO> subscriptions, LocalDate date) {
        return subscriptions.stream()
                .mapToDouble(subscription -> calculateSubscriptionSpending(subscription, date))
                .sum();
    }

    @Override
    public double calculateSubscriptionSpending(SubscriptionDTO subscriptionDTO, LocalDate date) {
        int daysInMonth = YearMonth.from(date).lengthOfMonth();
        int daysInYear = Year.from(date).length();

        double billingFrequency;
        switch (subscriptionDTO.getType()) {
            case DAILY:
                billingFrequency = daysInMonth;
                break;
            case WEEKLY:
                billingFrequency = (double) daysInMonth / 7;
                break;
            case BIMONTHLY:
                billingFrequency = 2;
                break;
            case MONTHLY:
                billingFrequency = 1;
                break;
            case YEARLY:
                billingFrequency = (double) 1 / 12;
                break;
            default:
                billingFrequency = 1;
                break;
        }

        return subscriptionDTO.getPrice() * billingFrequency;
    }
}
