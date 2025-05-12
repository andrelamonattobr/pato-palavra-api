package com.pato_palavra.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pato_palavra.entities.UserEntity;
import com.pato_palavra.entities.WordEntity;
import com.pato_palavra.entities.UserWordEntity;
import com.pato_palavra.repositories.UserRepository;
import com.pato_palavra.repositories.WordRepository;
import com.pato_palavra.repositories.UserWordRepository;
import com.pato_palavra.dtos.WordRegisterResponseDTO;

import java.util.Optional;

@Service
public class WordService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private WordRepository wordRepository;
    
    @Autowired
    private UserWordRepository userWordRepository;
    
    public WordRegisterResponseDTO registerWord(String nickname, String password, String word) {

        Optional<UserEntity> user = userRepository.findByNickname(nickname);
        
        if (user.isEmpty()) {
            return new WordRegisterResponseDTO(true, "User not found");
        }

        if (user.get().getTryAttempts() <= 0) {
            return new WordRegisterResponseDTO(true, "No attempts left");
        }

        WordEntity wordEntity = wordRepository.findByWord(word);
        if (wordEntity == null) {
            decreaseUserAttempt(user.get());
            return new WordRegisterResponseDTO(true, "Word not found");
        }
        
        Long userId = user.get().getId();
        String wordStr = wordEntity.getWord();
        Long idValue = user.get().getTryAttempts().longValue();

        UserWordEntity existingAttemptEntry = userWordRepository.findByUserIdAndWordWordAndId(userId, wordStr, idValue);
        if (existingAttemptEntry != null) {
            decreaseUserAttempt(user.get());
            return new WordRegisterResponseDTO(false, "Word already tried in this attempt");
        }
        
        /*
        UserWordEntity existingEntry = userWordRepository.findByUserIdAndWordWord(userId, wordStr);
        
        if (existingEntry != null) {
            return new WordRegisterResponseDTO(false, "Word already registered for this user");
        }*/

        UserWordEntity userWord = new UserWordEntity();
        userWord.setUser(user.get());
        userWord.setWord(wordEntity);
        userWord.setId(idValue);
        userWordRepository.save(userWord);
        return new WordRegisterResponseDTO(true, "Word registered successfully");

    }
    
    private void decreaseUserAttempt(UserEntity user) {
        user.setTryAttempts(user.getTryAttempts() - 1);
        userRepository.save(user);
    }

} 