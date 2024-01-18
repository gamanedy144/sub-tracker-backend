package com.dissertation.subtrackerbackend.repository;

import com.dissertation.subtrackerbackend.domain.Subscription;
import com.dissertation.subtrackerbackend.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllByTimestampBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);
    List<Transaction> findAllByTimestampBetweenAndSubscription(LocalDateTime startOfDay, LocalDateTime endOfDay, Subscription subscription);



    List<Transaction> findAllBySubscription(Subscription subscription);

    List<Transaction> findAllBySubscriptionIn(List<Subscription> subscriptions);

    @Query("SELECT t FROM Transaction t " +
            "JOIN t.subscription s JOIN s.user u " +
            "WHERE u.email = :email")
    List<Transaction> findAllByUserEmail(String email);
}
