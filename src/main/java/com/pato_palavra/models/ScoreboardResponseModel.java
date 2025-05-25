package com.pato_palavra.models;


import java.util.List;

public class ScoreboardResponseModel {
    private boolean success;
    private String message;
    private List<ScoreModel> scores;

    public ScoreboardResponseModel() {
    }

    public ScoreboardResponseModel(boolean success, String message, List<ScoreModel> scores) {
        this.success = success;
        this.message = message;
        this.scores = scores;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ScoreModel> getScores() {
        return scores;
    }

    public void setScores(List<ScoreModel> scores) {
        this.scores = scores;
    }

}
