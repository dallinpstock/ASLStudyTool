package com.example.aslstudytool.models;

public class Word {
    private String word;
    private char letter;
    private String url;

    public Word(String word, char letter, String url) {
        this.word = word;
        this.letter = letter;
        this.url = url;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
