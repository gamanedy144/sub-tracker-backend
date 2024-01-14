package com.dissertation.subtrackerbackend.service.impl;

import com.dissertation.subtrackerbackend.domain.Transaction;
import com.dissertation.subtrackerbackend.domain.dto.TransactionDTO;
import com.dissertation.subtrackerbackend.domain.mapper.TransactionMapper;
import com.dissertation.subtrackerbackend.repository.TransactionRepository;
import com.dissertation.subtrackerbackend.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {

    TransactionRepository transactionRepository;
    TransactionMapper mapper;
    @Override
    public List<Transaction> findAll(){
        return transactionRepository.findAll();
    };

    @Override
    public List<TransactionDTO> findAllDtos(){
        return transactionRepository.findAll().stream().map(transaction -> mapper.toDto(transaction)).collect(Collectors.toList());
    };
}
