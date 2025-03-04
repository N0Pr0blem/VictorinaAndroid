package com.laba.viktorina.data.repository.impl;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.laba.viktorina.data.database.AppDatabase;
import com.laba.viktorina.data.database.UserDao;
import com.laba.viktorina.data.model.User;
import com.laba.viktorina.data.repository.UserRepository;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserRepositoryImpl implements UserRepository {

    private final UserDao userDao;
    private final ExecutorService executorService;
    private LiveData<List<User>> users;

    public UserRepositoryImpl(Application application) {
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        userDao = appDatabase.userDao();
        users = userDao.getAll();
        executorService = Executors.newSingleThreadExecutor();
    }

    @Override
    public LiveData<List<User>> getAll() {
        return users;
    }

    @Override
    public void save(User user) {
        executorService.execute(() -> userDao.save(user));
    }
}
