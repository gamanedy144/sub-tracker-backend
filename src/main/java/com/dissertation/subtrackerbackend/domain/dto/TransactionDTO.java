package com.dissertation.subtrackerbackend.domain.dto;

import com.dissertation.subtrackerbackend.domain.TransactionStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionDTO implements Serializable {
    private Long id;
    private SubscriptionDTO subscription;
    private LocalDateTime timestamp;
    private TransactionStatus status;
}
