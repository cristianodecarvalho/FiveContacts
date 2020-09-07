package com.example.fivecontacts.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.fivecontacts.main.model.Contact;
import com.example.fivecontacts.main.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class SharedPrefConfig {

    public static final String CONTACT_LIST = "contactList";
    public static final String USER_LIST = "userList";
    public static final String LOGGED_USER = "loggedUser";

    public static void writeLoggedUserInSharedPref(Context context, User loggedUser){
        Gson gson = new Gson();
        String jsonString = gson.toJson(loggedUser);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(LOGGED_USER, jsonString);
        editor.apply();
    }

    public static User readLoggedUserFromSharedPref(Context context) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        String jsonString = pref.getString(LOGGED_USER,"");

        Gson gson = new Gson();
        Type type = new TypeToken<User>() {}.getType();
        User loggedUser = gson.fromJson(jsonString, type);
        return loggedUser;
    }

    public static void removeLoggedUserFromSharedPref(Context context) {
        SharedPreferences pref = context.getSharedPreferences("com.example.fivecontacts.main", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.remove(LOGGED_USER);
        editor.apply();
    }

    public static void writeUserListInSharedPref(Context context, ArrayList<User> userList){
        Gson gson = new Gson();
        String jsonString = gson.toJson(userList);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(USER_LIST, jsonString);
        editor.apply();
    }

    public static ArrayList<User> readUserListFromSharedPref(Context context) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        String jsonString = pref.getString(USER_LIST,"");

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<User>>() {}.getType();
        ArrayList<User> list = gson.fromJson(jsonString, type);
        return list;
    }

    public static void removeUserListFromSharedPref(Context context) {
        SharedPreferences pref = context.getSharedPreferences("com.example.fivecontacts.main", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.remove(USER_LIST);
        editor.apply();
    }

    public static void writeContactListInSharedPref(Context context, ArrayList<Contact> list){
        Gson gson = new Gson();
        String jsonString = gson.toJson(list);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(CONTACT_LIST, jsonString);
        editor.apply();
    }

    public static ArrayList<Contact> readContactListFromSharedPref(Context context) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        String jsonString = pref.getString(CONTACT_LIST,"");



        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Contact>>() {}.getType();
        ArrayList<Contact> list = gson.fromJson(jsonString, type);
        Log.d("Shared","Problema ao ler");
        return list;
    }

    public static void removeContactListFromSharedPref(Context context) {
        SharedPreferences pref = context.getSharedPreferences("com.example.fivecontacts.main", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.remove(CONTACT_LIST);
        editor.apply();
    }

//    public static ArrayList<Contact> readFavoriteContactsListFromSharedPref(Context context) {
//        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
//        String jsonString = pref.getString(CONTACT_LIST,"");
//
//        Gson gson = new Gson();
//        Type type = new TypeToken<ArrayList<Contact>>() {}.getType();
//        ArrayList<Contact> list = gson.fromJson(jsonString, type);
//
//        ArrayList<Contact> favoriteContactsList = new ArrayList<Contact>();
//
//        for(int i = 0; i < list.size() ; i++) {
//            if(list.get(i).isFavorite()){
//                favoriteContactsList.add(list.get(i));
//            }
//        }
//
//        return favoriteContactsList;
//    }

}
