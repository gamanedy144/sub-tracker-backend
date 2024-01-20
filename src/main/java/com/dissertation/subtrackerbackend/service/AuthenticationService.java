package com.dissertation.subtrackerbackend.service;

import com.dissertation.subtrackerbackend.domain.AuthenticationRequest;
import com.dissertation.subtrackerbackend.domain.AuthenticationResponse;
import com.dissertation.subtrackerbackend.domain.ChangePasswordRequest;
import com.dissertation.subtrackerbackend.domain.RegisterRequest;

public interface AuthenticationService {

    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);

    AuthenticationResponse changePassword(ChangePasswordRequest request) throws Exception;
}
