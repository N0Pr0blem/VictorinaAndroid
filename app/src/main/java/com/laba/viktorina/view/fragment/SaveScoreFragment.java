package com.laba.viktorina.view.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.laba.viktorina.R;
import com.laba.viktorina.data.model.User;
import com.laba.viktorina.databinding.FragmentSaveScoreBinding;
import com.laba.viktorina.utils.NavigationListener;
import com.laba.viktorina.view_model.UserViewModel;

public class SaveScoreFragment extends Fragment {
    private NavigationListener navigationListener;
    private FragmentSaveScoreBinding binding;
    private UserViewModel userViewModel;
    private Integer coins;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_save_score, container, false);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        Bundle args = getArguments();

        if (args != null) {
            coins = args.getInt("score");
        }

        binding.btnSave.setOnClickListener(v -> {
            String username = binding.txtName.getText().toString();
            if(!username.isEmpty() && navigationListener!=null){
                userViewModel.save(new User(username,coins));
                navigationListener.navigateTo(R.id.action_saveScoreFragment_to_menuFragment,new Bundle());
            }
        });

        return binding.getRoot();
    }
}