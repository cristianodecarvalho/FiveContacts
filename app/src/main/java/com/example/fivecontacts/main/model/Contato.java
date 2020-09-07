package com.example.fivecontacts.main.model;

import java.io.Serializable;

public class Contato implements Serializable {

    String name;
    String phone;

    public Contato(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
