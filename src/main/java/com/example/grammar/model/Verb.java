package com.example.grammar.model;

public class Verb {
    private String baseForm;
    private String pastTense;

    public Verb(String baseForm, String pastTense) {
        this.baseForm = baseForm;
        this.pastTense = pastTense;
    }

    public String getBaseForm() {
        return baseForm;
    }

    public String getPastTense() {
        return pastTense;
    }
}