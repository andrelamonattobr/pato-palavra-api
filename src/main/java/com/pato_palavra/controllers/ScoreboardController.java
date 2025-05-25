package com.pato_palavra.controllers;

import com.pato_palavra.models.ScoreboardResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.pato_palavra.dtos.PersonalScoreboardRequestDTO;
import com.pato_palavra.services.ScoreboardService;

@RestController
@RequestMapping("/api/scoreboard")
public class ScoreboardController {
    
    @Autowired
    private ScoreboardService scoreboardService;
    
    @GetMapping("/general")
    public ResponseEntity<ScoreboardResponseModel> getGeneralScoreboard() {
        ScoreboardResponseModel response = scoreboardService.getGeneralScoreboard();
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/personal")
    public ResponseEntity<ScoreboardResponseModel> getPersonalScoreboard(@RequestBody PersonalScoreboardRequestDTO requestDTO) {
        ScoreboardResponseModel response = scoreboardService.getPersonalScoreboard(requestDTO.getNickname());
        
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }

    }

}
