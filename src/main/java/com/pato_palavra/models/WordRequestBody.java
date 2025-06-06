package com.pato_palavra.models;

public class WordRequestBody {

    private String word;

    public WordRequestBody() {
    }

    public WordRequestBody(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

}
