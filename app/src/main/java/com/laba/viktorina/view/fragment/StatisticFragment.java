package com.laba.viktorina.view.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.laba.viktorina.R;
import com.laba.viktorina.data.model.User;
import com.laba.viktorina.utils.NavigationListener;
import com.laba.viktorina.view.adapter.UserAdapter;
import com.laba.viktorina.view_model.UserViewModel;

import java.util.List;

public class StatisticFragment extends Fragment {

    private NavigationListener navigationListener;
    private UserViewModel userViewModel;
    private List<User> users;
    private RecyclerView recyclerView;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof NavigationListener) {
            navigationListener = (NavigationListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement NavigationListener");
        }
    }
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_statistic, container, false);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        userViewModel.getUsers().observe(getViewLifecycleOwner(), users -> {
            this.users = users;
            updateRecyclerView(users);
        });


        recyclerView = view.findViewById(R.id.score_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));


        Button btnToMain = view.findViewById(R.id.btn_to_main);
        btnToMain.setOnClickListener(v->{
            if(navigationListener!=null){
                navigationListener.navigateTo(R.id.action_statisticFragment_to_menuFragment,new Bundle());
            }
        });

        return view;
    }

    private void updateRecyclerView(List<User> users) {
        UserAdapter userAdapter = new UserAdapter(users);
        recyclerView.setAdapter(userAdapter);
    }
}