package com.example.thithu.model;

public class AnswerCheck {
    private int poisition;
    private String name;
    private boolean checkAnswer;

    public boolean isCheckAnswer() {
        return checkAnswer;
    }

    public void setCheckAnswer(boolean checkAnswer) {
        this.checkAnswer = checkAnswer;
    }

    private boolean checkSure;
    public AnswerCheck(int poisition) {
        this.poisition = poisition;
        this.name = "Quesition "+ (poisition+1);
        this.checkSure = false;
    }

    public int getPoisition() {
        return poisition;
    }

    public void setPoisition(int poisition) {
        this.poisition = poisition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCheckSure() {
        return checkSure;
    }

    public void setCheckSure(boolean checkSure) {
        this.checkSure = checkSure;
    }
}
