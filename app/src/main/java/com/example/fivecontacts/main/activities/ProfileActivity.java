package com.example.fivecontacts.main.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.fivecontacts.R;
import com.example.fivecontacts.main.SharedPrefConfig;
import com.example.fivecontacts.main.activities.ContactListActivity;
import com.example.fivecontacts.main.activities.FavoritesActivity;
import com.example.fivecontacts.main.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {

    private User user;

    TextView tv_name, tv_username, tv_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        user = SharedPrefConfig.readLoggedUserFromSharedPref(this);

        tv_name = findViewById(R.id.tv_name);
        tv_username = findViewById(R.id.tv_username);
        tv_email= findViewById(R.id.tv_email);

        tv_name.setText(user.getName());
        tv_username.setText(user.getUsername());
        tv_email.setText(user.getEmail());

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.profile);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.contacts:
                        startActivity(new Intent(getApplicationContext(), ContactListActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.add_contact:
                        startActivity(new Intent(getApplicationContext(), AddContactActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.favorites:
                        startActivity(new Intent(getApplicationContext(), FavoritesActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile:
                        return true;

                }
                return false;
            }
        });
    }
}