package com.pato_palavra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pato_palavra.entities.WordEntity;

@Repository
public interface WordRepository extends JpaRepository<WordEntity, String> {
    WordEntity findByWord(String word);
}
