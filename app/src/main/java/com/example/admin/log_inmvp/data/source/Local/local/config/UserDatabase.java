package com.example.admin.log_inmvp.data.source.Local.local.config;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.admin.log_inmvp.R;

public class UserDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "user_database";
    public static final String TABLE_NAME = "user";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final int VERSION = 1;

    private static final String CREATE_TABLLE = "CREATE TABLE IF NOT EXISTS "
            + TABLE_NAME + "("
            + COLUMN_USERNAME + " TEXT PRIMARY KEY,"
            + COLUMN_PASSWORD + " TEXT)";

    private static final String DROP_TABLLE = "DROP TABLE IF EXISTS "  + TABLE_NAME;


    public UserDatabase(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_TABLLE);
        onCreate(sqLiteDatabase);
    }
}
