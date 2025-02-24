package com.laba.viktorina.data.model;

import java.util.List;

public class Question {
    private final Long id;
    private final String name;
    private final String rightAnswer;
    private final List<String> wrongAnswers;
    private final DifficultyLevel difficulty;
    private final String hint;

    public Question(Long id, String name, String rightAnswer, List<String> wrongAnswers, DifficultyLevel difficulty, String hint) {
        this.id = id;
        this.name = name;
        this.rightAnswer = rightAnswer;
        this.wrongAnswers = wrongAnswers;
        this.difficulty = difficulty;
        this.hint = hint;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public List<String> getWrongAnswers() {
        return wrongAnswers;
    }

    public DifficultyLevel getDifficulty() {
        return difficulty;
    }

    public String getHint() {
        return hint;
    }
}
