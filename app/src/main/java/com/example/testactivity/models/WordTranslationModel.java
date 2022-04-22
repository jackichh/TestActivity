package com.example.testactivity.models;

public class WordTranslationModel {
    private String word;
    private String translation;

    public WordTranslationModel(String word, String translation) {
        this.word = word;
        this.translation = translation;
    }

    public WordTranslationModel() {
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }
}
