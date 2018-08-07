package com.example.admin.log_inmvp.data.source;

import com.example.admin.log_inmvp.data.model.User;

import java.util.List;

public interface UserDataSource {

    interface LocalDataSource{
        void addUser(User user);

        List<User> model();

        boolean checkUserPassword(String username, String password);

        boolean checkUserIfExist(String username);
    }

    interface RemoteDataSource{

    }
}
