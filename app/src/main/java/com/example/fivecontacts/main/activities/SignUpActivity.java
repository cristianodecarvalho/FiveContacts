package com.example.fivecontacts.main.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.fivecontacts.R;
import com.example.fivecontacts.main.SharedPrefConfig;
import com.example.fivecontacts.main.model.Contact;
import com.example.fivecontacts.main.model.User;

import java.util.ArrayList;

public class SignUpActivity extends AppCompatActivity {

    EditText editText_name, editText_username, editText_password, editText_email  ;
    Button btn_sign_up;

    private ArrayList<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editText_name = findViewById(R.id.edit_text_name);
        editText_username = findViewById(R.id.edit_text_username);
        editText_password = findViewById(R.id.edit_text_password);
        editText_email = findViewById(R.id.edit_text_email);
        btn_sign_up = findViewById(R.id.btn_sign_up);

        userList = SharedPrefConfig.readUserListFromSharedPref(this);
        if(userList == null){
            userList = new ArrayList<User>();
        }

        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User u = new User(editText_name.getText().toString(), editText_username.getText().toString(), editText_password.getText().toString(), editText_email.getText().toString());
                userList.add(u);
                SharedPrefConfig.writeUserListInSharedPref(getApplicationContext(),userList);

                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                overridePendingTransition(0,0);
            }
        });
    }
}