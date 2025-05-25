package com.pato_palavra.controllers;

import com.pato_palavra.models.AuthRequestModel;
import com.pato_palavra.models.AuthResponseModel;
import com.pato_palavra.models.RefreshTokenRequestModel;
import com.pato_palavra.services.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseModel> register(@RequestBody AuthRequestModel request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseModel> authenticate(@RequestBody AuthRequestModel request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthResponseModel> refreshToken(@RequestBody RefreshTokenRequestModel request) {
        return ResponseEntity.ok(authService.refreshToken(request));
    }

} 