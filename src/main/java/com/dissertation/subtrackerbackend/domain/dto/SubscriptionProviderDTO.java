package com.dissertation.subtrackerbackend.domain.dto;

import com.dissertation.subtrackerbackend.domain.Subscription;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionProviderDTO {
    private Long id;
    private String name;
    private String details;
    private List<Subscription> subscriptionList;
}
