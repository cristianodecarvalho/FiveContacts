package com.example.fivecontacts.main.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fivecontacts.R;
import com.example.fivecontacts.main.ContactListAdapter;
import com.example.fivecontacts.main.PhonebookAdapter;
import com.example.fivecontacts.main.SharedPrefConfig;
import com.example.fivecontacts.main.model.Contact;
import com.example.fivecontacts.main.model.Contato;
import com.example.fivecontacts.main.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddContactActivity extends AppCompatActivity {

    ListView lv_phonebook;
    ArrayList<Contact> contactList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        lv_phonebook = findViewById(R.id.lv_phonebook);
        contactList = new ArrayList<>();


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, 1);
        }else{
            getContact();
        }

        PhonebookAdapter adapter = new PhonebookAdapter(this, R.layout.custom_item_list_view, contactList);
        lv_phonebook.setAdapter(adapter);

        lv_phonebook.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                ArrayList<User> userList = SharedPrefConfig.readUserListFromSharedPref(getApplicationContext());

                User u = SharedPrefConfig.readLoggedUserFromSharedPref(getApplicationContext());
                List<Contact> contacts = u.getContacts();
                if(contacts == null){
                    contacts = new ArrayList<Contact>();
                }
                contacts.add(contactList.get(position));
                u.setContacts(contacts);
                SharedPrefConfig.writeLoggedUserInSharedPref( getApplicationContext() ,u);

                for (User user : userList) {
                    if (user.getUsername().equals(u.getUsername())) {
                        List<Contact> contactsFromLoggedUser = user.getContacts();
                        if(contactsFromLoggedUser == null){
                            contactsFromLoggedUser = new ArrayList<Contact>();
                        }
                        contactsFromLoggedUser.add(contactList.get(position));
                        user.setContacts(contacts);
                        SharedPrefConfig.writeUserListInSharedPref( getApplicationContext() , userList);
                    }
                }

                Toast.makeText(getApplicationContext(), (String)contactList.get(position).getName() + " adicionado!", Toast.LENGTH_LONG).show();
            }
        });


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.add_contact);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.contacts:
                        startActivity(new Intent(getApplicationContext(), ContactListActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.add_contact:
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

    private void getContact(){
        Cursor cursor =  getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null,null);
        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String mobile = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            Contact c = new Contact(name, mobile);
            contactList.add(c);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 1){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getContact();
            }
        }
    }
}