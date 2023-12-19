package com.dissertation.subtrackerbackend.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.EnumType;
import org.hibernate.annotations.Type;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "sub_tracker", name = "subscription")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subscription_generator")
    @SequenceGenerator(schema = "sub_tracker", name = "subscription_generator", sequenceName = "subscription_sequence", allocationSize = 1)
    private long id;

    @Column(name = "subscription_name")
    private String subscriptionName;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    @Type(PostgreSQLEnumType.class)
    private SubscriptionTypeEnum type;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private SubscriptionProvider provider;

}
