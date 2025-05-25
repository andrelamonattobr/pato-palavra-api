package com.pato_palavra.services;

import com.pato_palavra.models.ScoreModel;
import com.pato_palavra.models.ScoreboardResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pato_palavra.repositories.UserWordRepository;
import com.pato_palavra.repositories.UserRepository;
import com.pato_palavra.entities.UserEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ScoreboardService {

    @Autowired
    private UserWordRepository userWordRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    public ScoreboardResponseModel getGeneralScoreboard() {
        List<Object[]> results = userWordRepository.findTop15ByPointsDesc();
        List<ScoreModel> scores = new ArrayList<>();
        
        for (Object[] result : results) {
            scores.add(new ScoreModel(
                    (String) result[0],
                    (Long) result[1],
                    (Long) result[2]
            ));
        }
        
        return new ScoreboardResponseModel(true, "General scoreboard retrieved successfully", scores);
    }
    
    public ScoreboardResponseModel getPersonalScoreboard(String username) {
        Optional<UserEntity> user = userRepository.findByUsername(username);
        
        if (user.isEmpty())
            return new ScoreboardResponseModel(true, "User not found", null);
        
        List<Object[]> results = userWordRepository.findUserAttemptsByNickname(username);
        List<ScoreModel> scores = new ArrayList<>();
        
        for (Object[] result : results) {
            scores.add(new ScoreModel(
                    (String) result[0],
                    (Long) result[1],
                    (Long) result[2]
            ));
        }
        
        return new ScoreboardResponseModel(true, "Personal scoreboard retrieved successfully", scores);

    }

}
