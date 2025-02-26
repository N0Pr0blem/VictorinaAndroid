package com.laba.viktorina.view.fragment;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.laba.viktorina.R;
import com.laba.viktorina.data.model.DifficultyLevel;
import com.laba.viktorina.databinding.FragmentQuestionBinding;
import com.laba.viktorina.view_model.QuestionViewModel;

import java.util.List;
import java.util.Map;

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

    private FragmentQuestionBinding binding;
    private List<Button> buttons;

    private Map<Integer, Button> answers;

    public QuestionFragment(DifficultyLevel difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_question, container, false);
        binding.txtHelp.setVisibility(View.INVISIBLE);

        viewModel = new ViewModelProvider(this).get(QuestionViewModel.class);
        viewModel.generateQuestions(difficulty);


        buttons = List.of(binding.btnFirst,binding.btnSecond,binding.btnThird,binding.btnFourth);
        binding.btnFirst.setOnClickListener(v->answerClick(1));
        binding.btnSecond.setOnClickListener(v->answerClick(2));
        binding.btnThird.setOnClickListener(v->answerClick(3));
        binding.btnFourth.setOnClickListener(v->answerClick(4));
        binding.btnHint.setOnClickListener(v->hintOnClick());

        binding.setQuestion(viewModel);
        binding.setLifecycleOwner(this);

        return binding.getRoot();
    }

    public void answerClick(int index){
        buttons.get(index).setBackgroundTintList(ColorStateList.valueOf((viewModel.answerClick(index))?(Color.GREEN):Color.RED));
    }

    public void hintOnClick(){
        binding.txtHelp.setVisibility(View.VISIBLE);
    }

}