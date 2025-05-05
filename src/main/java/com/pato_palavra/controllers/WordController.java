package com.pato_palavra.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pato_palavra.dtos.WordRegisterRequestDTO;
import com.pato_palavra.dtos.WordRegisterResponseDTO;
import com.pato_palavra.services.WordService;

@RestController
@RequestMapping("/api/words")
public class WordController {
    
    @Autowired
    private WordService wordService;
    
    @PostMapping("/register")
    public ResponseEntity<WordRegisterResponseDTO> registerWord(@RequestBody WordRegisterRequestDTO requestDTO) {
        WordRegisterResponseDTO response = wordService.registerWord(
            requestDTO.getNickname(),
            requestDTO.getPassword(), 
            requestDTO.getWord()
        );
        
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }
} 