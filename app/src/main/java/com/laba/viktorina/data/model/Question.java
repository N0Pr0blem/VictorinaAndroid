package com.laba.viktorina.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Question {
    private Long id;
    private String name;
    private List<Answer> answers;
    private DifficultyLevel difficulty;
    private String hint;

    public Question() {
    }

    public Question(@JsonProperty("id") Long id,
                    @JsonProperty("name") String name,
                    @JsonProperty("rightAnswer") String rightAnswer,
                    @JsonProperty("wrongAnswers") List<String> wrongAnswers,
                    @JsonProperty("difficulty") DifficultyLevel difficulty,
                    @JsonProperty("hint") String hint) {
        this.id = id;
        this.name = name;
        answers = new ArrayList<>();
        answers.add(new Answer(rightAnswer, true));
        answers.addAll(
                wrongAnswers.stream()
                        .map(wrong -> new Answer(wrong, false))
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
