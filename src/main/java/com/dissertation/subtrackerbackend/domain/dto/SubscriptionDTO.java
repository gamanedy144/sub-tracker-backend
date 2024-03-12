package com.dissertation.subtrackerbackend.domain.dto;

import com.dissertation.subtrackerbackend.domain.*;
import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubscriptionDTO implements Serializable {
        private long id;
        private String subscriptionName;
        private SubscriptionTypeEnum type;
        private UserDTO user;
        private SubscriptionProviderDTO provider;
        private LocalDate startDate;
        private LocalDate endDate;
        private LocalDate nextOccurrenceDate;
        private LocalDate lastOccurrenceDate;
        private Float price;
        private SubscriptionCategory category;
        private boolean active;
}
