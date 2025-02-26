package com.laba.viktorina.utils;

import com.laba.viktorina.data.model.DifficultyLevel;
import com.laba.viktorina.data.model.Question;
import com.laba.viktorina.data.repository.QuestionRepository;
import com.laba.viktorina.data.repository.impl.QuestionRepositoryImpl;

import java.util.Collections;
import java.util.List;

public class QuestionGeneratorImpl implements QuestionGenerator{

    private QuestionRepository questionRepository = new QuestionRepositoryImpl();
    private final int QUESTIONS_COUNT = 10;

    @Override
    public List<Question> generate(DifficultyLevel difficulty) {

        List<Question> questions = questionRepository.findAllByDifficulty(difficulty);
        Collections.shuffle(questions);

        return questions.subList(0,QUESTIONS_COUNT);
    }

}
