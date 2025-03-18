package com.laba.viktorina.view.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.laba.viktorina.R;
import com.laba.viktorina.databinding.FragmentResultBinding;
import com.laba.viktorina.utils.NavigationListener;

public class ResultFragment extends Fragment {

    private Integer coins;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentResultBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_result, container, false);
        Bundle args = getArguments();
        if (args != null) {
            coins = args.getInt("score");
        }
        binding.txtCoins.setText(coins.toString());
        binding.btnBackToMain.setOnClickListener(v->{
            if(navigationListener!=null){
                navigationListener.navigateTo(R.id.action_resultFragment_to_menuFragment,new Bundle());
            }
        });

        binding.btnSaveScore.setOnClickListener(v->{
            if(navigationListener!=null){
                navigationListener.navigateTo(R.id.action_resultFragment_to_saveScoreFragment,args);
            }
        });

        return binding.getRoot();
    }
}