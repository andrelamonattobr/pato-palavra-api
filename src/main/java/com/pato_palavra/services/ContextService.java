package com.pato_palavra.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class ContextService {

    public String getCurrentUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated())
            throw new RuntimeException("Unauthenticated user");

        Object principal = authentication.getPrincipal();

        return ((UserDetails) principal).getUsername();

    }

}
