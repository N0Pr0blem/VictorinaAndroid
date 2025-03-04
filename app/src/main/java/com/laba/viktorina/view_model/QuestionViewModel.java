package com.laba.viktorina.view_model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.laba.viktorina.data.model.DifficultyLevel;
import com.laba.viktorina.data.model.Question;
import com.laba.viktorina.utils.QuestionGeneratorImpl;

import java.util.List;

public class QuestionViewModel extends ViewModel {
    private List<Question> generatedQuestions;
    private MutableLiveData<Question> currentQuestion = new MutableLiveData<>();
    private Integer questionCount;
    private Integer coinCount;


    public Integer getQuestionCount() {
        return questionCount+1;
    }

    public Integer getCoinCount() {
        return coinCount;
    }

    public void generateQuestions(DifficultyLevel difficultyLevel) {
        generatedQuestions = new QuestionGeneratorImpl().generate(difficultyLevel);
        currentQuestion.setValue(generatedQuestions.get(0));
        questionCount = 0;
        coinCount = 0;
    }

    public LiveData<Question> getCurrentQuestion() {
        return currentQuestion;
    }

    public boolean next() {
        questionCount++;
        if (questionCount >= generatedQuestions.size()) {
            questionCount = 0;
            return false;
        }
        currentQuestion.setValue(generatedQuestions.get(questionCount));
        return true;
    }

    public boolean checkAnswer(int index, boolean hintIsOnClicked) {
        if (currentQuestion.getValue().getAnswers().get(index).isRight()) {
            coinCount += (hintIsOnClicked) ? 1 : 3;
            return true;
        }
        return false;
    }

}
