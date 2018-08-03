package com.example.admin.log_inmvp.data.repository;

import com.example.admin.log_inmvp.data.model.User;
import com.example.admin.log_inmvp.data.source.Local.local.UserLocalDataSource;
import com.example.admin.log_inmvp.data.source.UserDataSource;

import java.util.List;

public class UserRepository implements UserDataSource.LocalDataSource, UserDataSource.RemoteDataSource{

    private UserLocalDataSource mLocalDataSource;


    public UserRepository(UserLocalDataSource localDataSource) {
        mLocalDataSource = localDataSource;
    }

    @Override
    public void addUser(User user) {
        mLocalDataSource.addUser(user);
    }

    @Override
    public List<User> model() {
        return mLocalDataSource.model();
    }

    @Override
    public boolean checkUserPassword(String username, String password) {
        return mLocalDataSource.checkUserPassword(username, password);
    }

    @Override
    public boolean checkUserIfExist(String username) {
        return checkUserIfExist(username);
    }
}
