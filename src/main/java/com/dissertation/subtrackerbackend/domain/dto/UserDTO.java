package com.dissertation.subtrackerbackend.domain.dto;

import com.dissertation.subtrackerbackend.domain.Subscription;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String profilePicturePath;
    private List<Subscription> subscriptionList;
}
