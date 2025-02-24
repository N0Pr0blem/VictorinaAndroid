package com.laba.viktorina.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.laba.viktorina.R;
import com.laba.viktorina.data.model.DifficultyLevel;

public class MenuFragment extends Fragment {

    private Button btnEasy;
    private Button btnNormal;
    private Button btnHard;
    private Button btnImpossible;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        btnEasy = view.findViewById(R.id.difficulty_easy);
        btnNormal = view.findViewById(R.id.difficulty_normal);
        btnHard = view.findViewById(R.id.difficulty_hard);
        btnImpossible = view.findViewById(R.id.difficulty_impossible);

        btnEasy.setOnClickListener(v->startOnClick(DifficultyLevel.EASY));
        btnNormal.setOnClickListener(v->startOnClick(DifficultyLevel.NORMAL));
        btnHard.setOnClickListener(v->startOnClick(DifficultyLevel.HARD));
        btnImpossible.setOnClickListener(v->startOnClick(DifficultyLevel.IMPOSSIBLE));

        return view;
    }

    private void startOnClick(DifficultyLevel difficulty) {
        QuestionFragment questionFragment = new QuestionFragment(difficulty);

        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.main_fragment_container, questionFragment)
                .commit();
    }
}