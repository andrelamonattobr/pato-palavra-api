package com.pato_palavra.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pato_palavra.entities.UserEntity;
import com.pato_palavra.repositories.UserRepository;
import com.pato_palavra.dtos.AuthDTO;
import com.pato_palavra.dtos.AuthResponseDTO;

@Service
public class AuthService {
    
    @Autowired
    private UserRepository userRepository;
    
    public AuthResponseDTO login(AuthDTO authDTO) {
        UserEntity user = userRepository.findByNickname(authDTO.getNickname());
        
        if (user == null) {
            return new AuthResponseDTO(null, authDTO.getNickname(), false, "User not found", null);
        }
        
        if (!user.getPassword().equals(authDTO.getPassword())) {
            return new AuthResponseDTO(null, authDTO.getNickname(), false, "Invalid password", null);
        }
        
        return new AuthResponseDTO(user.getId(), user.getNickname(), true, "Login successful", user.getTryAttempts());
    }
    
    public AuthResponseDTO register(AuthDTO authDTO) {
        UserEntity existingUser = userRepository.findByNickname(authDTO.getNickname());
        
        if (existingUser != null) {
            return new AuthResponseDTO(null, authDTO.getNickname(), false, "Username already exists", null);
        }
        
        UserEntity newUser = new UserEntity();
        newUser.setNickname(authDTO.getNickname());
        newUser.setPassword(authDTO.getPassword());
        
        UserEntity savedUser = userRepository.save(newUser);
        
        return new AuthResponseDTO(savedUser.getId(), savedUser.getNickname(), true, "Registration successful", savedUser.getTryAttempts());
    }
    
    public boolean validateCredentials(String nickname, String password) {
        UserEntity user = userRepository.findByNickname(nickname);
        
        if (user == null) {
            return false;
        }
        
        return user.getPassword().equals(password);
    }
} 