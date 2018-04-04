package com.mekhti.quiz.Entity;

import java.util.List;

public class Question {

    private String text;
    private List<String> choices ;
    private String answer;

    public Question(String text, List<String> choices, String answer) {
        this.text = text;
        this.choices = choices;
        this.answer = answer;
    }

    public Question() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getChoices() {
        return choices;
    }

    public void setChoices(List<String> choices) {
        this.choices = choices;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
