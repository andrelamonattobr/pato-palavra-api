package com.pato_palavra.dtos;

public class WordRegisterRequestDTO {
    private String nickname;
    private String password;
    private String word;
    
    public WordRegisterRequestDTO() {
    }
    
    public WordRegisterRequestDTO(String nickname, String password, String word) {
        this.nickname = nickname;
        this.password = password;
        this.word = word;
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
    
    public String getWord() {
        return word;
    }
    
    public void setWord(String word) {
        this.word = word;
    }
} 