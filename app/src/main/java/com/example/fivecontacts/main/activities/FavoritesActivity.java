package com.example.fivecontacts.main.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;

import com.example.fivecontacts.R;
import com.example.fivecontacts.main.ContactListAdapter;
import com.example.fivecontacts.main.SharedPrefConfig;
import com.example.fivecontacts.main.model.Contact;
import com.example.fivecontacts.main.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class FavoritesActivity extends AppCompatActivity {

    private User user;

    Switch sw_keepConnected;
    Button btn_logout;

    private static final String TAG = "FavoritesActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        user = SharedPrefConfig.readLoggedUserFromSharedPref(this);

        sw_keepConnected = findViewById(R.id.sw_keepConnected);
        btn_logout = findViewById(R.id.btn_logout);

        sw_keepConnected.setChecked(user.isKeepConnected());


        sw_keepConnected.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                user.setKeepConnected(b);
                SharedPrefConfig.writeLoggedUserInSharedPref(getApplicationContext(), user);
            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPrefConfig.removeLoggedUserFromSharedPref(getApplicationContext());
                User u = SharedPrefConfig.readLoggedUserFromSharedPref(getApplicationContext());
                u.setKeepConnected(false);
                SharedPrefConfig.writeLoggedUserInSharedPref( getApplicationContext(),u);
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
                finishAffinity();
            }
        });


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.favorites);
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
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });
    }
}