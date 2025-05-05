package com.pato_palavra.dtos;

public class ScoreboardEntryDTO {
    private String nickname;
    private Long attemptId;
    private Long points;
    
    public ScoreboardEntryDTO() {
    }
    
    public ScoreboardEntryDTO(String nickname, Long attemptId, Long points) {
        this.nickname = nickname;
        this.attemptId = attemptId;
        this.points = points;
    }
    
    public String getNickname() {
        return nickname;
    }
    
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
    public Long getAttemptId() {
        return attemptId;
    }
    
    public void setAttemptId(Long attemptId) {
        this.attemptId = attemptId;
    }
    
    public Long getPoints() {
        return points;
    }
    
    public void setPoints(Long points) {
        this.points = points;
    }
} 