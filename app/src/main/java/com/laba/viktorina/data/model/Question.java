package com.laba.viktorina.data.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Question {
    private final Long id;
    private final String name;
    private final List<Answer> answers;
    private final DifficultyLevel difficulty;
    private final String hint;

    public Question(Long id, String name, String rightAnswer, List<String> wrongAnswers, DifficultyLevel difficulty, String hint) {
        this.id = id;
        this.name = name;
        answers = new ArrayList<>();
        answers.add(new Answer(rightAnswer,true));
        answers.addAll(
                wrongAnswers.stream()
                .map(wrong->new Answer(wrong,false))
                .collect(Collectors.toList())
        );
        Collections.shuffle(answers);
        this.difficulty = difficulty;
        this.hint = hint;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public DifficultyLevel getDifficulty() {
        return difficulty;
    }

    public String getHint() {
        return hint;
    }
}
