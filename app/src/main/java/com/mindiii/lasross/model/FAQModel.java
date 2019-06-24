package com.mindiii.lasross.model;

public class FAQModel {

    String question;
    String answer;

    public FAQModel(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}
