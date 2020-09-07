package com.example.fivecontacts.main.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {

    String name;
    String username;
    String password;
    String email;
    boolean keepConnected = false;
    List<Contact> contacts;

    public User(String name, String username, String password, String email) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() { return username; }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isKeepConnected() {
        return keepConnected;
    }

    public void setKeepConnected(boolean keepConnected) {
        this.keepConnected = keepConnected;
    }

    public List<Contact> getContacts() { return contacts; }

    public void setContacts(List<Contact> contacts) { this.contacts = contacts; }
}
