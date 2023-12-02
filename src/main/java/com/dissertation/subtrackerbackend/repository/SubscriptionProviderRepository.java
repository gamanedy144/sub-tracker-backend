package com.dissertation.subtrackerbackend.repository;

import com.dissertation.subtrackerbackend.domain.SubscriptionProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionProviderRepository extends JpaRepository<SubscriptionProvider, Long> {
}
