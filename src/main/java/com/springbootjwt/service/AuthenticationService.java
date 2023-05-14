package com.springbootjwt.service;

import com.springbootjwt.model.AuthenticationRequest;
import com.springbootjwt.model.AuthenticationResponse;
import com.springbootjwt.model.RegisterRequest;

public interface AuthenticationService {

    AuthenticationResponse register(RegisterRequest registerRequest);
    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
}
