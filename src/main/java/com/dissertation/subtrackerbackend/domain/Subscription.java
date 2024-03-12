package com.dissertation.subtrackerbackend.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.EnumType;
import org.hibernate.annotations.Type;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
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

    @Column
    private LocalDate startDate = LocalDate.now(ZoneId.of("Europe/Bucharest"));

    @Column
    private LocalDate endDate;

    @Column
    private LocalDate nextOccurrenceDate;

    @Column
    private LocalDate lastOccurrenceDate;

    @Column
    private Float price;

    @Column
    @Enumerated(EnumType.STRING)
    private SubscriptionCategory category;

    @Column
    private boolean active;

    public LocalDate calculateNextOccurrenceDate(LocalDate date) {
        LocalDate nextOccurrenceDate =  type.label.equals("yearly") ? date.plusYears(1)
                : type.label.equals("monthly") ? date.plusMonths(1)
                : type.label.equals("bimonthly") ? date.plusWeeks(2)
                : type.label.equals("weekly") ? date.plusWeeks(1)
                : type.label.equals("daily") ? date.plusDays(1)
                : date;
        if (Objects.nonNull(endDate) && (nextOccurrenceDate.isAfter(endDate) || nextOccurrenceDate.isEqual(endDate))) {
            nextOccurrenceDate = endDate;
        }
//        else if (nextOccurrenceDate.isBefore(LocalDate.now())) {
//            nextOccurrenceDate = calculateNextOccurrenceDate(nextOccurrenceDate);
//        }
        return nextOccurrenceDate;
    }

    @PrePersist
    private void prePersist() {
        nextOccurrenceDate = calculateNextOccurrenceDate(startDate);
        if (nextOccurrenceDate.isBefore(LocalDate.now())) {
            while (nextOccurrenceDate.isBefore(LocalDate.now())) {
                lastOccurrenceDate = nextOccurrenceDate;
                nextOccurrenceDate = calculateNextOccurrenceDate(nextOccurrenceDate);
            }
        }
        else {
            lastOccurrenceDate = startDate;
        }
    }
}
