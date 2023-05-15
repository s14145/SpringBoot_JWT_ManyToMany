package com.springbootjwt.controller;


import com.springbootjwt.model.AuthenticationRequest;
import com.springbootjwt.model.AuthenticationResponse;
import com.springbootjwt.model.RegisterRequest;
import com.springbootjwt.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private Logger log = LogManager.getLogger(AuthenticationController.class);

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest){
        log.info("AuthenticationController:register entered");
        AuthenticationResponse authenticationResponse = authenticationService.register(registerRequest);
        log.info("AuthenticationController:register exited");
        return new ResponseEntity<>(authenticationResponse, HttpStatus.CREATED);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest){
        log.info("AuthenticationController:authenticate entered");
        AuthenticationResponse authenticationResponse = authenticationService.authenticate(authenticationRequest);
        log.info("AuthenticationController:authenticate exited");
        return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
    }
}