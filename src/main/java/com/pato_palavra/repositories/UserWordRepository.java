package com.pato_palavra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.pato_palavra.entities.UserWordEntity;
import java.util.List;

@Repository
public interface UserWordRepository extends JpaRepository<UserWordEntity, Long> {

    @Query("SELECT uw.user.username as user, uw.id as id, COUNT(uw) as count " +
            "FROM UserWordEntity uw " +
            "GROUP BY uw.user.username, uw.id " +
            "ORDER BY count DESC")
    List<Object[]> countWordsByUserAndId();

    @Query("SELECT uw.user.username as user, uw.id as id, COUNT(uw) as count " +
            "FROM UserWordEntity uw " +
            "GROUP BY uw.user.username, uw.id " +
            "ORDER BY count DESC " +
            "LIMIT 15")
    List<Object[]> findTop15ByPointsDesc();

    @Query("SELECT uw.user.username as user, uw.id as id, COUNT(uw) as count " +
            "FROM UserWordEntity uw " +
            "WHERE uw.user.username = ?1 " +
            "GROUP BY uw.user.username, uw.id " +
            "ORDER BY count DESC")
    List<Object[]> findUserAttemptsByUsername(String username);

    @Query("SELECT uw FROM UserWordEntity uw WHERE uw.user.id = ?1 AND uw.word.word = ?2 AND uw.id = ?3")
    UserWordEntity findByUserIdAndWordWordAndId(Long userId, String word, Long id);

}
