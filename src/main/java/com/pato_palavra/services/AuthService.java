package com.pato_palavra.services;

import com.pato_palavra.dtos.AuthRequest;
import com.pato_palavra.dtos.AuthResponse;
import com.pato_palavra.dtos.RefreshTokenRequest;
import com.pato_palavra.dtos.RegisterRequest;
import com.pato_palavra.entities.UserEntity;
import com.pato_palavra.repositories.UserRepository;
import com.pato_palavra.security.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            AuthenticationManager authenticationManager
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.findByNickname(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        
        var user = new UserEntity();
        user.setNickname(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setTryAttempts(3);

        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user.getNickname());
        var refreshToken = jwtService.generateRefreshToken(user.getNickname());
        
        return new AuthResponse(jwtToken, refreshToken);
    }

    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByNickname(request.getUsername())
                .orElseThrow(() -> new BadCredentialsException("Invalid username or password"));

        var jwtToken = jwtService.generateToken(user.getNickname());
        var refreshToken = jwtService.generateRefreshToken(user.getNickname());
        
        return new AuthResponse(jwtToken, refreshToken);
    }

    public AuthResponse refreshToken(RefreshTokenRequest request) {
        String refreshToken = request.getRefreshToken();
        try {
            String username = jwtService.extractUsername(refreshToken);
            
            var user = userRepository.findByNickname(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            
            if (jwtService.isTokenValid(refreshToken, user.getNickname())) {
                var accessToken = jwtService.generateToken(user.getNickname());
                
                return new AuthResponse(accessToken, refreshToken);
            }
        } catch (ExpiredJwtException ex) {
            throw new RuntimeException("Refresh token expired");
        }
        
        throw new RuntimeException("Invalid refresh token");
    }
} 