package com.example.admin.log_inmvp.screen.main;

import com.example.admin.log_inmvp.data.model.User;
import com.example.admin.log_inmvp.data.repository.UserRepository;
import com.example.admin.log_inmvp.data.source.Local.local.UserLocalDataSource;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mView;
    private UserRepository mUserRepository;
    private UserLocalDataSource mLocalDataSource;

    public MainPresenter(MainContract.View view, UserRepository userRepository) {
        mView = view;
        mUserRepository = userRepository;
    }

    @Override
    public void checkLoginCorrection(String username, String password) {
        //mUserRepository = new UserRepository();

        if (mUserRepository.checkUserPassword(username, password)){
            mView.loginSuccess(username);
        }else {
            mView.loginFail();
        }
    }

    @Override
    public void addDataBase(User user) {
        mUserRepository.addUser(user);
    }
}
