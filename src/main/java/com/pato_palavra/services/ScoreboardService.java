package com.pato_palavra.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pato_palavra.repositories.UserWordRepository;
import com.pato_palavra.repositories.UserRepository;
import com.pato_palavra.entities.UserEntity;
import com.pato_palavra.dtos.ScoreboardEntryDTO;
import com.pato_palavra.dtos.ScoreboardResponseDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScoreboardService {

    @Autowired
    private UserWordRepository userWordRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    public ScoreboardResponseDTO getGeneralScoreboard() {
        List<Object[]> results = userWordRepository.findTop15ByPointsDesc();
        List<ScoreboardEntryDTO> entries = new ArrayList<>();
        
        for (Object[] result : results) {
            String nickname = (String) result[0];
            Long id = (Long) result[1];
            Long count = (Long) result[2];
            
            entries.add(new ScoreboardEntryDTO(nickname, id, count));
        }
        
        return new ScoreboardResponseDTO(true, "General scoreboard retrieved successfully", entries);
    }
    
    public ScoreboardResponseDTO getPersonalScoreboard(String nickname, String password) {
        // Validate user credentials
        UserEntity user = userRepository.findByNickname(nickname);
        
        if (user == null) {
            return new ScoreboardResponseDTO(false, "User not found", null);
        }
        
        if (!user.getPassword().equals(password)) {
            return new ScoreboardResponseDTO(false, "Invalid credentials", null);
        }
        
        List<Object[]> results = userWordRepository.findUserAttemptsByNickname(nickname);
        List<ScoreboardEntryDTO> entries = new ArrayList<>();
        
        for (Object[] result : results) {
            String userNickname = (String) result[0];
            Long id = (Long) result[1];
            Long count = (Long) result[2];
            
            entries.add(new ScoreboardEntryDTO(userNickname, id, count));
        }
        
        return new ScoreboardResponseDTO(true, "Personal scoreboard retrieved successfully", entries);
    }
} 