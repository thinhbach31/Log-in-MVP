package com.example.admin.log_inmvp.screen.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.log_inmvp.R;
import com.example.admin.log_inmvp.data.model.User;
import com.example.admin.log_inmvp.data.repository.UserRepository;
import com.example.admin.log_inmvp.data.source.Local.local.UserLocalDataSource;
import com.example.admin.log_inmvp.data.source.Local.local.config.UserDatabase;
import com.example.admin.log_inmvp.screen.login.LoginActivity;

public class MainActivity extends AppCompatActivity implements MainContract.View, View.OnClickListener {

    private EditText mEditUsername;
    private EditText mEditPassword;
    private Button mButtonLogin;
    private Button mButtonSignin;
    private MainPresenter mMainPresenter;
    private UserDatabase mUserDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditUsername = findViewById(R.id.edit_username);
        mEditPassword = findViewById(R.id.edit_password);
        mButtonLogin  = findViewById(R.id.button_login);
        mButtonSignin = findViewById(R.id.button_signin);

        mButtonLogin.setOnClickListener(this);
        mButtonSignin.setOnClickListener(this);

        mUserDatabase = new UserDatabase(MainActivity.this);
        UserRepository userRepository = new UserRepository(new UserLocalDataSource(mUserDatabase));

        mMainPresenter = new MainPresenter(this, userRepository);
    }

    @Override
    public void onClick(View view) {
        String name = mEditUsername.getText().toString();
        String pass = mEditPassword.getText().toString();
        switch (view.getId()){
            case R.id.button_login:

                mMainPresenter.checkLoginCorrection(name,pass);
                clearText();
                Intent intent = new Intent(this, LoginActivity.class);
                intent.putExtra("name", name);
                startActivity(intent);
                break;
            case R.id.button_signin:

                User model = new User();
                try {
                    model.setUsername(name);
                    model.setPassword(pass);
                }catch (Exception e){

                }

                if (mUserDatabase == null){
                    mMainPresenter.addDataBase(model);
                    Toast.makeText(this, "Add success", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void loginSuccess(String username) {
        Toast.makeText(this,
                "Login successful!", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void loginFail() {
        Toast.makeText(this, String.valueOf(R.string.login_fail), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void clearText() {
        mEditUsername.setText("");
        mEditPassword.setText("");
    }


}
