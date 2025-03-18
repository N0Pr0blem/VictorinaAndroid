package com.laba.viktorina.utils;

import android.content.Context;

import com.laba.viktorina.data.model.DifficultyLevel;
import com.laba.viktorina.data.model.Question;
import com.laba.viktorina.data.repository.QuestionRepository;
import com.laba.viktorina.data.repository.impl.QuestionRepositoryImpl;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class QuestionGeneratorImpl implements QuestionGenerator{

    private QuestionRepository questionRepository;
    private final int QUESTIONS_COUNT = 10;

    public QuestionGeneratorImpl(Context context) throws IOException {
        questionRepository = new QuestionRepositoryImpl(context);
    }

    @Override
    public List<Question> generate(DifficultyLevel difficulty) {

        List<Question> questions = questionRepository.findAllByDifficulty(difficulty);
        Collections.shuffle(questions);

        return questions.subList(0,QUESTIONS_COUNT);
    }

}
