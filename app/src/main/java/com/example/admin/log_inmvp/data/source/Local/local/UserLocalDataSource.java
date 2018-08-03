package com.example.admin.log_inmvp.data.source.Local.local;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.admin.log_inmvp.data.model.User;
import com.example.admin.log_inmvp.data.source.Local.local.config.UserDatabase;
import com.example.admin.log_inmvp.data.source.UserDataSource;

import java.util.ArrayList;
import java.util.List;

public class UserLocalDataSource implements UserDataSource.LocalDataSource{

    private UserDatabase mUserDatabase;
    private static final String SELECT_TABLE = "SELECT * FROM " + UserDatabase.TABLE_NAME;


    public UserLocalDataSource(UserDatabase userDatabase) {
        mUserDatabase = userDatabase;
    }

    @Override
    public void addUser(User user) {
        SQLiteDatabase database = mUserDatabase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserDatabase.COLUMN_USERNAME, user.getUsername());
        contentValues.put(UserDatabase.COLUMN_PASSWORD, user.getPassword());
        database.insert(UserDatabase.TABLE_NAME, null, contentValues);
        database.close();
    }

    @Override
    public List<User> model() {
        List<User> models = new ArrayList<>();
        SQLiteDatabase database = mUserDatabase.getReadableDatabase();

        String getAllData = SELECT_TABLE;
        Cursor cursor = database.rawQuery(getAllData,null);
        cursor.moveToFirst();
        while (cursor.moveToNext()){
            User user = new User();
            user.setUsername(cursor.getString(0));
            user.setPassword(cursor.getString(1));
        }
        cursor.close();
        database.close();
        return models;
    }

    @Override
    public boolean checkUserPassword(String username, String password) {
        String getAllDataQuery = SELECT_TABLE;
        SQLiteDatabase database = mUserDatabase.getWritableDatabase();
        boolean STATE = false;
        Cursor cursor = database.rawQuery(getAllDataQuery, null);
        cursor.moveToFirst();
        while (cursor.moveToNext()){
            String name = cursor.getString(0);
            String pass = cursor.getString(1);
            if (name.equals(username) && pass.equals(password)){
                STATE = true;
            }
        }
            cursor.close();
            database.close();
            return STATE;
    }

    @Override
    public boolean checkUserIfExist(String username) {
        String getAllData = SELECT_TABLE;
        SQLiteDatabase database = mUserDatabase.getReadableDatabase();
        boolean STATE = false;
        Cursor cursor = database.rawQuery(getAllData, null);
        cursor.moveToFirst();
        while (cursor.moveToNext()){
            String name = cursor.getString(0);
            if(name.equals(username)){
                STATE = true;
                cursor.close();
                database.close();
                return STATE;
            }
        }
        cursor.close();
        database.close();
        return STATE;
    }
}
