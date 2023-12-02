package com.dissertation.subtrackerbackend.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(schema = "sub_tracker", name = "subscription")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subscription_generator")
    @SequenceGenerator(schema = "sub_tracker", name = "subscription_generator", sequenceName = "subscription_sequence", allocationSize = 1)

    private long id;

    @Column(name = "subscription_name")
    private String subscriptionName;

    @Column(name = "type")
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id")
    private SubscriptionProvider provider;
}
