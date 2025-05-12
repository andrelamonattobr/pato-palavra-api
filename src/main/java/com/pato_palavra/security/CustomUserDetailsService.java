package com.pato_palavra.security;

import com.pato_palavra.entities.UserEntity;
import com.pato_palavra.repositories.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByNickname(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        
        return new User(
                user.getNickname(),
                user.getPassword(),
                new ArrayList<>()
        );
    }
} 