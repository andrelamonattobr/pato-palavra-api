package com.pato_palavra.models;

public class ScoreModel {
    private String username;
    private Long attemptId;
    private Long points;

    public ScoreModel(){

    }

    public ScoreModel(String username, Long attemptId, Long points){
        this.username = username;
        this.attemptId = attemptId;
        this.points = points;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
