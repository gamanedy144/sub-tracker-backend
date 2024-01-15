package com.dissertation.subtrackerbackend.service.impl;

import com.dissertation.subtrackerbackend.config.JwtService;
import com.dissertation.subtrackerbackend.domain.Subscription;
import com.dissertation.subtrackerbackend.domain.Transaction;
import com.dissertation.subtrackerbackend.domain.dto.SubscriptionDTO;
import com.dissertation.subtrackerbackend.domain.dto.TransactionDTO;
import com.dissertation.subtrackerbackend.domain.mapper.TransactionMapper;
import com.dissertation.subtrackerbackend.repository.TransactionRepository;
import com.dissertation.subtrackerbackend.service.TransactionService;
import com.dissertation.subtrackerbackend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {

    TransactionRepository transactionRepository;
    TransactionMapper mapper;
    JwtService jwtService;
    UserService userService;

    @Override
    public List<Transaction> findAll(){
        return transactionRepository.findAll();
    };

    @Override
    public List<TransactionDTO> findAllDtos() {
        return transactionRepository.findAll()
                .stream().map(transaction -> mapper.toDto(transaction))
                .collect(Collectors.toList());
    };

    @Override
    public List<TransactionDTO> findAllDtosForCurrentUser() {
        return transactionRepository.findAllByUserEmail(jwtService.getUsername())
                .stream().map(transaction -> mapper.toDto(transaction))
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionDTO> findBySubscription(Subscription subscription) {
        return transactionRepository.findAllBySubscription(subscription)
                .stream().map(transaction -> mapper.toDto(transaction))
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionDTO> findBySubscriptions(List<Subscription> subscriptions) {
        return transactionRepository.findAllBySubscriptionIn(subscriptions)
                .stream().map(transaction -> mapper.toDto(transaction))
                .collect(Collectors.toList());
    }
}
