package com.example.grammar.model;

public class Noun {
    private String word;
    private boolean isPlural;

    public Noun(String word, boolean isPlural) {
        this.word = word;
        this.isPlural = isPlural;
    }

    public String getWord() {
        return word;
    }

    public boolean isPlural() {
        return isPlural;
    }
}