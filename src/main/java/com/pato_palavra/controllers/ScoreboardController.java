package com.pato_palavra.controllers;

import com.pato_palavra.models.ScoreboardResponseModel;
import com.pato_palavra.services.ContextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.pato_palavra.services.ScoreboardService;

@RestController
@RequestMapping("/api/scoreboard")
public class ScoreboardController {
    
    @Autowired
    private ScoreboardService scoreboardService;

    @Autowired
    private ContextService contextService;

    @GetMapping("/general")
    public ResponseEntity<ScoreboardResponseModel> getGeneralScoreboard() {
        ScoreboardResponseModel response = scoreboardService.getGeneralScoreboard();
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/personal")
    public ResponseEntity<ScoreboardResponseModel> getPersonalScoreboard() {
        String user = contextService.getCurrentUsername();
        ScoreboardResponseModel response = scoreboardService.getPersonalScoreboard(user);
        
        if (response.isSuccess())
            return ResponseEntity.ok(response);
        else
            return ResponseEntity.badRequest().body(response);

    }

}
