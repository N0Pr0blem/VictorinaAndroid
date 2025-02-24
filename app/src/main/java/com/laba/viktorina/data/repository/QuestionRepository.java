package com.laba.viktorina.data.repository;

import com.laba.viktorina.data.model.DifficultyLevel;
import com.laba.viktorina.data.model.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository {
    Optional<Question> findById(Long id);
    List<Question> findAllByDifficulty(DifficultyLevel difficulty);
    boolean save(Question question);
    boolean save(List<Question> questions);
}
