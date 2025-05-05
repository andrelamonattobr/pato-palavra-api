package com.pato_palavra.dtos;

public class PersonalScoreboardRequestDTO {
    private String nickname;
    private String password;
    
    public PersonalScoreboardRequestDTO() {
    }
    
    public PersonalScoreboardRequestDTO(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }
    
    public String getNickname() {
        return nickname;
    }
    
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
} 