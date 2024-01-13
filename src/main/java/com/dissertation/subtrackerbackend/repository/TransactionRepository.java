package com.dissertation.subtrackerbackend.repository;

import com.dissertation.subtrackerbackend.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllByTimestampBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);
}
