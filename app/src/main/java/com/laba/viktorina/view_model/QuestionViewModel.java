package com.laba.viktorina.view_model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.laba.viktorina.data.model.DifficultyLevel;
import com.laba.viktorina.data.model.Question;
import com.laba.viktorina.data.repository.QuestionRepository;
import com.laba.viktorina.data.repository.impl.QuestionRepositoryImpl;
import com.laba.viktorina.utils.Randomizer;

import java.util.List;
import java.util.stream.Collectors;

public class QuestionViewModel extends ViewModel {
    private MutableLiveData<Question> currentQuestion = new MutableLiveData<>();
    private List<Question> generatedQuestions;
    private final QuestionRepository questionRepository = new QuestionRepositoryImpl();
    private final int QUESTIONS_COUNT = 10;

    private int currentQuestionNumber = 0;

    public int getCurrentQuestionNumber() {
        return currentQuestionNumber;
    }

    public LiveData<Question> getCurrentQuestion() {
        return currentQuestion;
    }

    public void generateQuestions(DifficultyLevel difficulty) {
        List<Question> questions = questionRepository.findAllByDifficulty(difficulty);
        List<Integer> generatedIndexes = Randomizer.generate(QUESTIONS_COUNT, questions.size());

        generatedQuestions = generatedIndexes.stream()
                .map(questions::get)
                .collect(Collectors.toList());
    }

    public boolean nextQuestion() {
        if (generatedQuestions == null || generatedQuestions.isEmpty()) {
            return false;
        }

        if (currentQuestionNumber >= generatedQuestions.size()) {
            currentQuestionNumber = 0;
            return false;
        }

        currentQuestion.setValue(generatedQuestions.get(currentQuestionNumber));
        currentQuestionNumber++;

        return true;
    }
}
