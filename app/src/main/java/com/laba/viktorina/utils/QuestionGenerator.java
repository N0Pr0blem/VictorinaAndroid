package com.laba.viktorina.utils;

import com.laba.viktorina.data.model.DifficultyLevel;
import com.laba.viktorina.data.model.Question;

import java.util.List;

public interface QuestionGenerator {
    List<Question> generate(DifficultyLevel difficultyLevel);
}
