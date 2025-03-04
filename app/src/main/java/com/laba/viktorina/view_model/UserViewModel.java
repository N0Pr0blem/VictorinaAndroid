package com.laba.viktorina.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.laba.viktorina.data.model.User;
import com.laba.viktorina.data.repository.UserRepository;
import com.laba.viktorina.data.repository.impl.UserRepositoryImpl;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    private LiveData<List<User>> users;
    private final UserRepository userRepository;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepositoryImpl(application);
        users = userRepository.getAll();
    }

    public LiveData<List<User>> getUsers(){
        return users;
    }

    public void save(User user){
        userRepository.save(user);
    }
}
