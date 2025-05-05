package com.pato_palavra.dtos;

public class AuthResponseDTO {
    private Long id;
    private String nickname;
    private boolean authenticated;
    private String message;
    private Integer tryAttempts;
    
    public AuthResponseDTO() {
    }
    
    public AuthResponseDTO(Long id, String nickname, boolean authenticated, String message, Integer tryAttempts) {
        this.id = id;
        this.nickname = nickname;
        this.authenticated = authenticated;
        this.message = message;
        this.tryAttempts = tryAttempts;
    }
    
    // Constructor without tryAttempts for backward compatibility
    public AuthResponseDTO(Long id, String nickname, boolean authenticated, String message) {
        this.id = id;
        this.nickname = nickname;
        this.authenticated = authenticated;
        this.message = message;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNickname() {
        return nickname;
    }
    
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
    public boolean isAuthenticated() {
        return authenticated;
    }
    
    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public Integer getTryAttempts() {
        return tryAttempts;
    }
    
    public void setTryAttempts(Integer tryAttempts) {
        this.tryAttempts = tryAttempts;
    }
} 