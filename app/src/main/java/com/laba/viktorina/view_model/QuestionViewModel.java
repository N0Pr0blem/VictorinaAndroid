package com.laba.viktorina.view_model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.laba.viktorina.data.dto.QuestionDto;
import com.laba.viktorina.data.model.DifficultyLevel;
import com.laba.viktorina.data.model.Question;
import com.laba.viktorina.utils.QuestionGeneratorImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionViewModel extends ViewModel {
    private MutableLiveData<QuestionDto> currentQuestion = new MutableLiveData<>();
    private List<Question> generatedQuestion;
    public int questionIndex = 0;
    private int rightAnswerCounter = 0;

    public void generateQuestions(DifficultyLevel difficulty) {
        generatedQuestion = new QuestionGeneratorImpl().generate(difficulty);
        setCurrentQuestion(generatedQuestion.get(questionIndex));
    }

    public LiveData<QuestionDto> getCurrentQuestion() {
        return currentQuestion;
    }

    private void setCurrentQuestion(Question question) {
        List<String> wrongAnswers = new ArrayList<>(question.getWrongAnswers());
        Collections.shuffle(wrongAnswers);

        currentQuestion.setValue(new QuestionDto(question.getName(),
                question.getRightAnswer(),
                new ArrayList<>(wrongAnswers.subList(0, 3)),
                question.getHint(),
                questionIndex + 1)
        );

    }

    public boolean next() {
        questionIndex++;
        if (questionIndex < generatedQuestion.size()) {
            setCurrentQuestion(generatedQuestion.get(questionIndex));
            return true;
        } else {
            questionIndex = 0;
            return false;
        }
    }

    public boolean answerClick(int index){
        if(currentQuestion.getValue().isRight(index)){
            rightAnswerCounter++;
            next();
            return true;
        }
        else{
            next();
            return false;
        }
    }
}
