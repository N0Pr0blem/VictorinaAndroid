package com.laba.viktorina.view.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.laba.viktorina.R;
import com.laba.viktorina.data.model.DifficultyLevel;
import com.laba.viktorina.databinding.FragmentMenuBinding;
import com.laba.viktorina.databinding.FragmentQuestionBinding;
import com.laba.viktorina.utils.NavigationListener;

public class MenuFragment extends Fragment {

    private NavigationListener navigationListener;

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
        FragmentMenuBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_menu, container, false);

        binding.difficultyEasy.setOnClickListener(v->{
            if (navigationListener != null) {
                Bundle bundle = new Bundle();
                bundle.putString("difficulty",DifficultyLevel.EASY.toString());
                navigationListener.navigateTo(R.id.action_menuFragment_to_questionFragment,bundle);
            }
        });

        return binding.getRoot();
    }

}