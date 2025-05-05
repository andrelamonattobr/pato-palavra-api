package com.pato_palavra.entities;

import java.io.Serializable;

public class UserWordId implements Serializable {
    private Long user;
    private String word;
    private Long id;

    public UserWordId() {
    }

    public UserWordId(Long user, String word, Long id) {
        this.user = user;
        this.word = word;
        this.id = id;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserWordId that = (UserWordId) o;

        if (!user.equals(that.user)) return false;
        if (!word.equals(that.word)) return false;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        int result = user.hashCode();
        result = 31 * result + word.hashCode();
        result = 31 * result + id.hashCode();
        return result;
    }
}
