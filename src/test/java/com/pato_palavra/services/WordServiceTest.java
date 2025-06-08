package com.pato_palavra.services;

import com.pato_palavra.models.WordRegisterResponseModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import com.pato_palavra.entities.UserEntity;
import com.pato_palavra.entities.WordEntity;
import com.pato_palavra.entities.UserWordEntity;
import com.pato_palavra.repositories.UserRepository;
import com.pato_palavra.repositories.WordRepository;
import com.pato_palavra.repositories.UserWordRepository;

public class WordServiceTest {

    @Mock
    private UserRepository userRepository;
    
    @Mock
    private WordRepository wordRepository;
    
    @Mock
    private UserWordRepository userWordRepository;
    
    @InjectMocks
    private WordService wordService;
    
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void testRegisterWord_UserNotFound() {
        // Arrange
        /*String username = "nonExistentUser";
        String word = "test";
        
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());
        
        // Act
        WordRegisterResponseModel response = wordService.registerWord(username, word);
        
        // Assert
        assertTrue(response.isSuccess());
        assertEquals("User not found", response.getMessage());
        verify(userRepository).findByUsername(username);
        verifyNoInteractions(wordRepository);
        verifyNoInteractions(userWordRepository);*/
    }
    
    @Test
    public void testRegisterWord_NoAttemptsLeft() {
        /*// Arrange
        String username = "testUser";
        String password = "password";
        String word = "test";
        
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setUsername(username);
        user.setPassword(password);
        user.setTryAttempts(0);
        
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        
        // Act
        WordRegisterResponseModel response = wordService.registerWord(username, word);
        
        // Assert
        assertTrue(response.isSuccess());
        assertEquals("No attempts left", response.getMessage());
        verify(userRepository).findByUsername(username);
        verifyNoInteractions(wordRepository);
        verifyNoInteractions(userWordRepository);*/
    }
    
    @Test
    public void testRegisterWord_WordNotFound() {
        /*// Arrange
        String username = "testUser";
        String password = "password";
        String word = "nonExistentWord";
        
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setUsername(username);
        user.setPassword(password);
        user.setTryAttempts(3);
        
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        when(wordRepository.findByWord(word)).thenReturn(null);
        
        // Act
        WordRegisterResponseModel response = wordService.registerWord(username, word);
        
        // Assert
        assertTrue(response.isSuccess());
        assertEquals("Word not found", response.getMessage());
        verify(userRepository).findByUsername(username);
        verify(wordRepository).findByWord(word);
        verify(userRepository).save(user);
        assertEquals(2, user.getTryAttempts());
        verifyNoInteractions(userWordRepository);*/
    }
    
    @Test
    public void testRegisterWord_WordAlreadyTriedInThisAttempt() {
        /*// Arrange
        String username = "testUser";
        String password = "password";
        String word = "repeatedWord";
        
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setUsername(username);
        user.setPassword(password);
        user.setTryAttempts(2);
        
        WordEntity wordEntity = new WordEntity();
        wordEntity.setWord(word);
        
        UserWordEntity existingEntry = new UserWordEntity();
        existingEntry.setUser(user);
        existingEntry.setWord(wordEntity);
        
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        when(wordRepository.findByWord(word)).thenReturn(wordEntity);
        //when(userWordRepository.findByUserIdAndWordWordAndId(1L, word)).thenReturn(existingEntry);
        
        // Act
        WordRegisterResponseModel response = wordService.registerWord(username, word);
        
        // Assert
        //assertFalse(response.isSuccess());
        //assertEquals("Word already tried in this attempt", response.getMessage());
        //verify(userRepository).findByUsername(username);
        //verify(wordRepository).findByWord(word);
        //verify(userWordRepository).findByUserIdAndWordWordAndId(1L, word);
        //verify(userRepository).save(user);
        //assertEquals(1, user.getTryAttempts());*/
    }
    
    @Test
    public void testRegisterWord_SuccessfulRegistration() {
        /*// Arrange
        String username = "testUser";
        String password = "password";
        String word = "validWord";
        
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setUsername(username);
        user.setPassword(password);
        user.setTryAttempts(3);
        
        WordEntity wordEntity = new WordEntity();
        wordEntity.setWord(word);
        
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        when(wordRepository.findByWord(word)).thenReturn(wordEntity);
        //when(userWordRepository.findByUserIdAndWordWordAndId(1L, word)).thenReturn(null);
        
        // Act
        WordRegisterResponseModel response = wordService.registerWord(username, word);
        
        // Assert
        assertTrue(response.isSuccess());
        assertEquals("Word registered successfully", response.getMessage());
        verify(userRepository).findByUsername(username);
        verify(wordRepository).findByWord(word);
        //verify(userWordRepository).findByUserIdAndWordWordAndId(1L, word);
        verify(userWordRepository).save(any(UserWordEntity.class));*/

    }

}
