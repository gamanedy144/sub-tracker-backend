package com.dissertation.subtrackerbackend.domain.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCredentials {

    private String email;
    private String password;
}
