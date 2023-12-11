package com.dissertation.subtrackerbackend.domain;

import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.EnumType;
import org.hibernate.annotations.Type;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "sub_tracker", name = "subscription")
//@TypeDef(
//        name = "subscription_type_enum",
//        typeClass = PostgreSQLEnumType.class
//)
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id")
    private SubscriptionProvider provider;

}
