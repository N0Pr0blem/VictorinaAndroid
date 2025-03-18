package com.laba.viktorina.data.repository;

import androidx.lifecycle.LiveData;

import com.laba.viktorina.data.model.User;

import java.util.List;

public interface UserRepository {
    LiveData<List<User>>getAll();
    void save(User user);
}
