package com.example.EcoAqua.documentMappers;

import com.example.EcoAqua.models.Customer;
import com.example.EcoAqua.models.WaterBox;
import org.bson.Document;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomerMapper {
    public CustomerMapper(){
    }
    public static Document customerToDocument(Customer customer){
        return new Document(
                "_id",customer.getId()
        ).append(
                "email",customer.getEmail()
        ).append(
                "password",customer.getPassword()
        ).append(
                "registeredDevices",
                new ArrayList<>(customer.getRegisteredDevices().keySet())
        );
    }

}
