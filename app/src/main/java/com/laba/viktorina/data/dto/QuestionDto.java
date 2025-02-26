package com.laba.viktorina.data.dto;

import java.util.Collections;
import java.util.List;

public class QuestionDto {
    private final String name;
    private final String rightAnswer;
    private final List<String> answers;
    private final String hint;
    private final String number;

    public QuestionDto(String name,String rightAnswer, List<String> answers, String hint, int number) {
        this.name = name;
        this.answers = answers;
        this.hint = hint;
        this.rightAnswer = rightAnswer;
        this.number = String.valueOf(number);
        answers.add(rightAnswer);
        Collections.shuffle(answers);
    }

    public String getName() {
        return name;
    }
    public String getRightAnswer() {
        return rightAnswer;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public String getHint() {
        return hint;
    }

    public String getNumber() {
        return number;
    }

    public boolean isRight(int index){
        return answers.get(index).equals(rightAnswer);
    }

}
