package com.pato_palavra.services;

import com.pato_palavra.models.WordRegisterResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pato_palavra.entities.UserEntity;
import com.pato_palavra.entities.WordEntity;
import com.pato_palavra.entities.UserWordEntity;
import com.pato_palavra.repositories.UserRepository;
import com.pato_palavra.repositories.WordRepository;
import com.pato_palavra.repositories.UserWordRepository;

import java.util.Optional;

@Service
public class WordService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private WordRepository wordRepository;
    
    @Autowired
    private UserWordRepository userWordRepository;
    
    public WordRegisterResponseModel registerWord(String username, String word) {

        Optional<UserEntity> user = userRepository.findByUsername(username);
        
        if (user.isEmpty())
            return new WordRegisterResponseModel(true, "User not found");

        if (user.get().getTryAttempts() <= 0)
            return new WordRegisterResponseModel(true, "No attempts left");

        WordEntity wordEntity = wordRepository.findByWord(word);
        if (wordEntity == null) {
            decreaseUserAttempt(user.get());
            return new WordRegisterResponseModel(true, "Word not found");
        }
        
        Long userId = user.get().getId();
        String wordStr = wordEntity.getWord();

        UserWordEntity existingAttemptEntry = userWordRepository.findByUserIdAndWordWordAndId(userId, wordStr);
        if (existingAttemptEntry != null) {
            decreaseUserAttempt(user.get());
            return new WordRegisterResponseModel(false, "Word already tried in this attempt");
        }

        UserWordEntity userWord = new UserWordEntity();
        userWord.setUser(user.get());
        userWord.setWord(wordEntity);
        userWordRepository.save(userWord);
        return new WordRegisterResponseModel(true, "Word registered successfully");

    }
    
    private void decreaseUserAttempt(UserEntity user) {
        user.setTryAttempts(user.getTryAttempts() - 1);
        userRepository.save(user);
    }

}
