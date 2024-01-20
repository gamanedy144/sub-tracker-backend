package com.dissertation.subtrackerbackend.repository;

import com.dissertation.subtrackerbackend.domain.Subscription;
import com.dissertation.subtrackerbackend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    List<Subscription> findAllByUser(User user);

    Integer countAllByUser(User user);
}
