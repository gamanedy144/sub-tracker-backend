package com.dissertation.subtrackerbackend.service;

import com.dissertation.subtrackerbackend.domain.Subscription;
import com.dissertation.subtrackerbackend.domain.Transaction;
import com.dissertation.subtrackerbackend.domain.dto.SubscriptionDTO;
import com.dissertation.subtrackerbackend.domain.dto.TransactionDTO;

import java.util.List;

public interface TransactionService {
    List<Transaction> findAll();

    List<TransactionDTO> findAllDtos();

    List<TransactionDTO> findAllDtosForCurrentUser();

    List<TransactionDTO> findBySubscription(Subscription subscription);

    List<TransactionDTO> findBySubscriptions(List<Subscription> subscription);
}
