package com.pato_palavra.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.pato_palavra.dtos.PersonalScoreboardRequestDTO;
import com.pato_palavra.dtos.ScoreboardResponseDTO;
import com.pato_palavra.services.ScoreboardService;

@RestController
@RequestMapping("/api/scoreboard")
public class ScoreboardController {
    
    @Autowired
    private ScoreboardService scoreboardService;
    
    @GetMapping("/general")
    public ResponseEntity<ScoreboardResponseDTO> getGeneralScoreboard() {
        ScoreboardResponseDTO response = scoreboardService.getGeneralScoreboard();
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/personal")
    public ResponseEntity<ScoreboardResponseDTO> getPersonalScoreboard(@RequestBody PersonalScoreboardRequestDTO requestDTO) {
        ScoreboardResponseDTO response = scoreboardService.getPersonalScoreboard(
            requestDTO.getNickname(),
            requestDTO.getPassword()
        );
        
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }
} 