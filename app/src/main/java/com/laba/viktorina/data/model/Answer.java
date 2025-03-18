package com.laba.viktorina.data.model;

public class Answer {
    private final String title;
    private final Boolean isRight;

    public Answer(String title, Boolean isRight) {
        this.title = title;
        this.isRight = isRight;
    }

    public String getTitle() {
        return title;
    }

    public Boolean isRight() {
        return isRight;
    }
}
