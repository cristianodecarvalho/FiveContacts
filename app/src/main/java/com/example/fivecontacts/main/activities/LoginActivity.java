package com.example.fivecontacts.main.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.fivecontacts.R;
import com.example.fivecontacts.main.model.Contact;
import com.example.fivecontacts.main.model.User;

import com.example.fivecontacts.main.SharedPrefConfig;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    EditText editText_login, editText_password;
    Switch sw_keepConnected;
    Button signup,signin;
    SharedPreferences sharedPreferences;

    String name, password, keepConnected;

    private List<User> userList;
    private User loggedUser;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_LOGIN = "login";
    private static final String KEY_PASSWORD = "password";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editText_login = findViewById(R.id.edit_text_login);
        editText_password = findViewById(R.id.edit_text_password);
        signin = findViewById(R.id.btn_sign_in);
        signup = findViewById(R.id.btn_sign_up);
        sw_keepConnected = findViewById(R.id.sw_keepConnected);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        userList = SharedPrefConfig.readUserListFromSharedPref(this);

        loggedUser = SharedPrefConfig.readLoggedUserFromSharedPref(this);

        try {

            if(loggedUser.isKeepConnected()){
                Log.d("keep", "true");
                Intent i = new Intent(LoginActivity.this, ContactListActivity.class);
                startActivity(i);
                finish();
            }else{
                Log.d("keep", "false");
            }
        } catch (Exception e) {

        }



        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (User user : userList) {
                    if (user.getUsername().equals(editText_login.getText().toString()) && user.getPassword().equals(editText_password.getText().toString())) {
                        user.setKeepConnected(sw_keepConnected.isChecked());
                        SharedPrefConfig.writeLoggedUserInSharedPref(getApplicationContext(),user);
                        Intent i = new Intent(LoginActivity.this, ContactListActivity.class);
                        startActivity(i);
                        finish();
                        break;
                    }
                }

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(i);
                finish();
            }
        });


    }
}