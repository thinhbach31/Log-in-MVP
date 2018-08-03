package com.example.admin.log_inmvp.screen.main;

import com.example.admin.log_inmvp.data.model.User;

public interface MainContract {

    interface View{

        //toast login success
        void loginSuccess(String username);

        //toast login fail
        void loginFail();

        //clear text when login success
        void clearText();
    }

    interface Presenter{

        //set view
        //void setView(View view);

        //check for username and password
        void checkLoginCorrection(String username, String password);

        void addDataBase(User user);
    }
}
