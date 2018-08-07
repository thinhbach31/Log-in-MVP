package com.example.admin.log_inmvp.screen.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.admin.log_inmvp.R;

public class LoginActivity extends AppCompatActivity {

    private TextView mTextHello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mTextHello = findViewById(R.id.text_Hello);

        Intent intent = getIntent();
        mTextHello.setText(intent.getStringExtra("name"));
    }
}
