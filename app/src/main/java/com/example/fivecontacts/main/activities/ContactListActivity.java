package com.example.fivecontacts.main.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.fivecontacts.R;
import com.example.fivecontacts.main.ContactListAdapter;
import com.example.fivecontacts.main.SharedPrefConfig;
import com.example.fivecontacts.main.model.Contact;
import com.example.fivecontacts.main.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class ContactListActivity extends AppCompatActivity {

    private static final int REQUEST_CALL = 1;

    private int position ;

    private List<Contact> contactList;
    private ListView listViewContacts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        listViewContacts = findViewById(R.id.list_view_contacts);
        User u = SharedPrefConfig.readLoggedUserFromSharedPref(this);

        listViewContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                position = i;
                makePhoneCall();
            }
        });


        try {
            contactList =  u.getContacts();
            for (int i = 0; i < contactList.size(); i++){
                Log.d("ContatoLista", contactList.get(i).getName());
            }
            if(contactList.size() > 0) {
                ContactListAdapter adapter = new ContactListAdapter(this, R.layout.custom_item_list_view, contactList);
                listViewContacts.setAdapter(adapter);
            }
        }catch (Exception e ){
            Log.d("Excecao",e.getMessage());
        }


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.contacts);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.contacts:
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
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });
    }

    private void makePhoneCall(){
        if (ContextCompat.checkSelfPermission(ContactListActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ContactListActivity.this, new String[] {Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        }else{
            String s = "tel:" + contactList.get(position).getPhone();
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(s));
        startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CALL){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                makePhoneCall();
            }else{
                Intent intent = new Intent(Intent.ACTION_DIAL);
                startActivity(intent);
            }
        }
    }
}