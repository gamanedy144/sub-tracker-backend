package com.dissertation.subtrackerbackend.service;

import com.dissertation.subtrackerbackend.domain.Transaction;
import com.dissertation.subtrackerbackend.domain.dto.TransactionDTO;

import java.util.List;

public interface TransactionService {
    List<Transaction> findAll();

    List<TransactionDTO> findAllDtos();
}
