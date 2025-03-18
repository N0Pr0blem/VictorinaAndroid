package com.laba.viktorina.data.repository.impl;

import android.content.Context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laba.viktorina.data.model.DifficultyLevel;
import com.laba.viktorina.data.model.Question;
import com.laba.viktorina.data.repository.QuestionRepository;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class QuestionRepositoryImpl implements QuestionRepository {

    private final List<Question> questions;

    public QuestionRepositoryImpl(Context context) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        InputStream inputStream = context.getAssets().open("questions.json");
        questions = mapper.readValue(inputStream,mapper.getTypeFactory().constructCollectionType(List.class,Question.class));
    }

    @Override
    public Optional<Question> findById(Long id) {
        return questions.stream()
                .filter(question -> question.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Question> findAllByDifficulty(DifficultyLevel difficulty) {
        return questions.stream()
                .filter(question -> question.getDifficulty().equals(difficulty))
                .collect(Collectors.toList());
    }

    @Override
    public boolean save(Question question) {
        questions.add(question);
        return true;
    }

    @Override
    public boolean save(List<Question> questions) {
        this.questions.addAll(questions);
        return true;
    }

}
