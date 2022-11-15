package com.example.EcoAqua.models;

import org.bson.types.ObjectId;

import java.util.HashMap;
import java.util.Map;

public class Customer {

    private ObjectId id;
    private String email;
    private String password;
    private HashMap<String,WaterBox> registeredDevices;

    public Customer(){

    }
    public ObjectId getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public HashMap<String, WaterBox> getRegisteredDevices() {
        return registeredDevices;
    }

}
