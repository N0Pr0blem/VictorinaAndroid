package com.laba.viktorina.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.laba.viktorina.R;
import com.laba.viktorina.data.model.DifficultyLevel;
import com.laba.viktorina.databinding.FragmentQuestionBinding;
import com.laba.viktorina.utils.NavigationListener;
import com.laba.viktorina.view_model.QuestionViewModel;

import java.util.List;

public class QuestionFragment extends Fragment {
    private NavigationListener navigationListener;
    private FragmentQuestionBinding binding;
    private QuestionViewModel viewModel;
    private DifficultyLevel difficulty;
    private CountDownTimer timer;
    private List<Button> btns;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof NavigationListener) {
            navigationListener = (NavigationListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement NavigationListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_question, container, false);
        viewModel = new ViewModelProvider(this).get(QuestionViewModel.class);

        startTimer();

        Bundle args = getArguments();
        if (args != null) {
            String difficultyName = args.getString("difficulty");
            difficulty = DifficultyLevel.valueOf(difficultyName);
        }

        viewModel.generateQuestions(difficulty);

        binding.txtHelp.setVisibility(View.INVISIBLE);
        binding.btnNext.setVisibility(View.INVISIBLE);

        binding.btnHint.setOnClickListener(v -> binding.txtHelp.setVisibility(View.VISIBLE));

        btns = List.of(binding.btnFirst, binding.btnSecond, binding.btnThird, binding.btnFourth);

        binding.btnFirst.setOnClickListener(v -> answerClick(0, btns.get(0)));
        binding.btnSecond.setOnClickListener(v -> answerClick(1, btns.get(1)));
        binding.btnThird.setOnClickListener(v -> answerClick(2, btns.get(2)));
        binding.btnFourth.setOnClickListener(v -> answerClick(3, btns.get(3)));
        binding.btnNext.setOnClickListener(v -> setNext());

        binding.txtQuestionTitle.setText(String.valueOf(viewModel.getQuestionCount()));

        binding.setQuestion(viewModel);
        binding.setLifecycleOwner(this);

        return binding.getRoot();
    }

    private void setNext() {
        if (viewModel.next()) {
            binding.btnNext.setVisibility(View.INVISIBLE);
            binding.txtHelp.setVisibility(View.INVISIBLE);
            btns.forEach(but -> {
                but.setClickable(true);
                but.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.primary));
            });
            binding.txtQuestionTitle.setText(String.valueOf(viewModel.getQuestionCount()));
            startTimer();
        }
        else{
            if(navigationListener!=null) {
                Bundle bundle = new Bundle();
                bundle.putInt("score", viewModel.getCoinCount());
                bundle.putString("difficulty", difficulty.name());
                navigationListener.navigateTo(R.id.action_questionFragment_to_resultFragment,bundle);
            }
        }
    }

    private void answerClick(int index, Button btn) {
        boolean isRight = viewModel.checkAnswer(index, binding.txtHelp.getVisibility() == View.VISIBLE);
        int colorResId = isRight ? R.color.helper : R.color.primaryVariant;
        btn.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), colorResId));
        binding.btnNext.setVisibility(View.VISIBLE);
        btns.forEach(but -> but.setClickable(false));
        timer.cancel();
    }

    private void startTimer(){
        timer = new CountDownTimer(15000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                binding.txtTimer.setText(String.valueOf(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                setNext();
            }
        }.start();
    }

}