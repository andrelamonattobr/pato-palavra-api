package com.pato_palavra.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "user_words")
@IdClass(UserWordIdClass.class)
public class UserWordEntity {
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Id
    @ManyToOne
    @JoinColumn(name = "word", nullable = false)
    private WordEntity word;

    @Id
    @Column(nullable = false)
    private Long id;

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public WordEntity getWord() {
        return word;
    }

    public void setWord(WordEntity word) {
        this.word = word;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
