package com.pato_palavra.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContextService {

    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated())
            throw new RuntimeException("Unauthenticated user");

        String username = "";
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else if (principal instanceof Optional<?>) {
            if (((Optional<?>) principal).isPresent() && ((Optional<?>) principal).get() instanceof UserDetails)
                username = ((UserDetails) ((Optional<?>) principal).get()).getUsername();
            else
                System.out.println("Not found user or optional");
        }else {
            System.out.println("Not found user or optional");
        }

        authentication.getPrincipal();

        return username;

    }

}
