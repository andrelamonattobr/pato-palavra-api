package com.pato_palavra.dtos;

import java.util.List;

public class ScoreboardResponseDTO {
    private boolean success;
    private String message;
    private List<ScoreboardEntryDTO> entries;
    
    public ScoreboardResponseDTO() {
    }
    
    public ScoreboardResponseDTO(boolean success, String message, List<ScoreboardEntryDTO> entries) {
        this.success = success;
        this.message = message;
        this.entries = entries;
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
    
    public List<ScoreboardEntryDTO> getEntries() {
        return entries;
    }
    
    public void setEntries(List<ScoreboardEntryDTO> entries) {
        this.entries = entries;
    }
} 