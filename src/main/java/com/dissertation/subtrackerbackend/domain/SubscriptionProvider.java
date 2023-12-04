package com.dissertation.subtrackerbackend.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "sub_tracker", name = "subscription_provider")
public class SubscriptionProvider {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subscription_provider_generator")
    @SequenceGenerator(schema = "sub_tracker", name = "subscription_provider_generator", sequenceName = "subscription_provider_sequence", allocationSize = 1)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "details")
    private String details;

    @OneToMany(mappedBy = "provider", fetch = FetchType.LAZY)
    private List<Subscription> subscriptionList;
}
