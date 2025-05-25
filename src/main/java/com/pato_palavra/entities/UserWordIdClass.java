package com.pato_palavra.entities;

import java.io.Serializable;

public class UserWordIdClass implements Serializable {
    private Long user;
    private String word;

    public UserWordIdClass() {
    }

    public UserWordIdClass(Long user, String word) {
        this.user = user;
        this.word = word;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserWordIdClass that = (UserWordIdClass) o;

        return (user.equals(that.user) && word.equals(that.word));
    }

    @Override
    public int hashCode() {
        int result = user.hashCode();
        result = 31 * result + word.hashCode();
        return result;
    }

}
