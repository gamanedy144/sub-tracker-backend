package com.dissertation.subtrackerbackend.service.impl;

import com.dissertation.subtrackerbackend.config.JwtService;
import com.dissertation.subtrackerbackend.domain.*;
import com.dissertation.subtrackerbackend.service.AuthenticationService;
import com.dissertation.subtrackerbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService service;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .appUsername(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        service.saveUser(user);
        String jwtToken = jwtService.generateToken(user);
        return  AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = service.findByEmail(request.getEmail());
        String jwtToken = jwtService.generateToken(user);
        return  AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse changePassword(ChangePasswordRequest request) throws Exception {
        User currentUser = service.getCurrentUser();
        if ( passwordEncoder.matches(request.getCurrentPassword()
                ,currentUser.getPassword()) ) {
            currentUser.setPassword(passwordEncoder.encode(request.getNewPassword()));
            service.saveUser(currentUser);
            String jwtToken = jwtService.generateToken(currentUser);

            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        }
        else throw new Exception("Passwords do not match");
    }
}
