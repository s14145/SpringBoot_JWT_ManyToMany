package com.springbootjwt.service;

import com.springbootjwt.entity.Roles;
import com.springbootjwt.entity.Users;
import com.springbootjwt.mapper.Mapper;
import com.springbootjwt.model.AuthenticationRequest;
import com.springbootjwt.model.AuthenticationResponse;
import com.springbootjwt.model.RegisterRequest;
import com.springbootjwt.repository.RoleRepository;
import com.springbootjwt.repository.UserRepository;
import com.springbootjwt.security.JwtHelperToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service("AuthenticationService")
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{

    private Logger log = LogManager.getLogger(AuthenticationServiceImpl.class);

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder bcryptPasswordEncoder;

    private final JwtHelperToken jwtHelperToken;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest registerRequest){
        log.info("AuthenticationServiceImpl:register method execution started");
        log.info("AuthenticationServiceImpl:register method request payload {} ", Mapper.mapToJsonString(registerRequest));
        Users user = new Users();
        user.setFirstname(registerRequest.getFirstname());
        user.setLastname(registerRequest.getLastname());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(bcryptPasswordEncoder.encode(registerRequest.getPassword()));
        user.setRoles(registerRequest.getRoles());
        userRepository.save(user);
        String token = jwtHelperToken.generateToken(user);
        log.info("AuthenticationServiceImpl:register method response payload {} ", token);
        log.info("AuthenticationServiceImpl:register method execution ended");
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest){
        log.info("AuthenticationServiceImpl:authenticate method execution started");
        log.info("AuthenticationServiceImpl:authenticate method request payload {} ", Mapper.mapToJsonString(authenticationRequest));
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        Users user = userRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow(() -> new RuntimeException("Email not found."));
        String token = jwtHelperToken.generateToken(user);
        log.info("AuthenticationServiceImpl:authenticate method response payload {} ", token);
        log.info("AuthenticationServiceImpl:authenticate method execution ended");
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }
}