package com.laba.viktorina.view.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.laba.viktorina.R;
import com.laba.viktorina.data.model.DifficultyLevel;
import com.laba.viktorina.data.model.Question;
import com.laba.viktorina.utils.Randomizer;
import com.laba.viktorina.view_model.QuestionViewModel;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class QuestionFragment extends Fragment {

    private QuestionViewModel viewModel;
    private DifficultyLevel difficulty;

    private Button btnFirst;
    private Button btnSecond;
    private Button btnThird;
    private Button btnFourth;
    private ImageButton btnHint;
    private TextView txtQuestion;
    private TextView txtHelp;
    private TextView txtTitle;

    private Map<Integer, Button> answers;

    public QuestionFragment(DifficultyLevel difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_question, container, false);
        viewModel = new ViewModelProvider(this).get(QuestionViewModel.class);
        viewModel.generateQuestions(difficulty);
        viewModel.nextQuestion();

        btnFirst = view.findViewById(R.id.btn_first);
        btnFirst.setOnClickListener(v->answerClick());

        btnSecond = view.findViewById(R.id.btn_second);
        btnSecond.setOnClickListener(v->answerClick());

        btnThird = view.findViewById(R.id.btn_third);
        btnThird.setOnClickListener(v->answerClick());

        btnFourth = view.findViewById(R.id.btn_fourth);
        btnFourth.setOnClickListener(v->answerClick());

        txtTitle= view.findViewById(R.id.txt_question_title);

        txtHelp = view.findViewById(R.id.txt_help);
        txtHelp.setVisibility(View.INVISIBLE);

        btnHint = view.findViewById(R.id.btn_hint);
        btnHint.setOnClickListener(v->txtHelp.setVisibility(View.VISIBLE));

        txtQuestion = view.findViewById(R.id.txt_question);

        answers = Map.of(
                0, btnFirst,
                1, btnSecond,
                2, btnThird,
                3, btnFourth
        );

        setQuestion();

        return view;
    }

    private void answerClick() {
        viewModel.nextQuestion();
    }

    @SuppressLint("SetTextI18n")
    public void setQuestion(){
        Question question = viewModel.getCurrentQuestion().getValue();

        List<Integer> wrongAnswers = Randomizer.generate(3,question.getWrongAnswers().size());
        List<String> answers = wrongAnswers.stream()
                .map(index-> question.getWrongAnswers().get(index))
                .collect(Collectors.toList());
        answers.add(question.getRightAnswer());
        mix(answers);
        txtHelp.setText(question.getHint());
        txtQuestion.setText(question.getName());
        txtTitle.setText("Вопрос №"+viewModel.getCurrentQuestionNumber());
    }

    private void mix(List<String> answers) {
        List<Integer> answerIndexes =  Randomizer.generate(4,4);
        for(int i =0;i<answers.size();i++)
        {
            this.answers.get(answerIndexes.get(i)).setText(answers.get(i));
        }
    }
}