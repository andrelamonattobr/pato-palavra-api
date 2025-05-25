package com.pato_palavra.services;

import com.pato_palavra.models.AuthRequestModel;
import com.pato_palavra.models.AuthResponseModel;
import com.pato_palavra.models.RefreshTokenRequestModel;
import com.pato_palavra.repositories.UserRepository;
import com.pato_palavra.entities.UserEntity;

import com.pato_palavra.security.JwtService;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthResponseModel register(AuthRequestModel request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent())
            throw new RuntimeException("Username already exists");

        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setTryAttempts(3);

        userRepository.save(user);

        return new AuthResponseModel(
                jwtService.generateToken(user.getUsername()),
                jwtService.generateRefreshToken(user.getUsername())
        );

    }

    public AuthResponseModel authenticate(AuthRequestModel request) {
        UserEntity user = userRepository.findByUsername(request.getUsername()).orElseThrow(() ->
                new BadCredentialsException("Invalid username or password")
        );

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw new BadCredentialsException("Invalid username or password");

        System.out.println("Before authenticationManager.authenticate()");
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), null));
        System.out.println("After authenticationManager.authenticate()");

        return new AuthResponseModel(
                jwtService.generateToken(user.getUsername()),
                jwtService.generateRefreshToken(user.getUsername())
        );

    }

    public AuthResponseModel refreshToken(RefreshTokenRequestModel request) {
        UserEntity user = userRepository.findByUsername(
                jwtService.extractUsername(request.getRefreshToken())
        ).orElseThrow(() ->
                new RuntimeException("Invalid refreshing token")
        );

        if (jwtService.isTokenValid(request.getRefreshToken(), user.getUsername())) {
            return new AuthResponseModel(
                    jwtService.generateToken(user.getUsername()),
                    jwtService.generateRefreshToken(user.getUsername())
            );
        }

        throw new RuntimeException("Invalid refreshing token");

    }

} 