package com.dissertation.subtrackerbackend.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
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

}
