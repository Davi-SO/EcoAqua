package com.example.EcoAqua.documentMappers;

import com.example.EcoAqua.Daos.WaterBoxDao;
import com.example.EcoAqua.models.Customer;
import com.example.EcoAqua.models.WaterBox;
import org.bson.Document;
import org.bson.types.ObjectId;

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
    public static Customer documentToCustomer(Document document){
        HashMap<String, WaterBox> waterboxes = new HashMap<>();
        WaterBoxDao wbdao= new WaterBoxDao();
        for(Object d:document.get("registeredDevices",ArrayList.class)){
            waterboxes.put(d.toString(),WaterBoxMapper.documentToWaterBox(wbdao.getWaterBox((ObjectId) d)));
        }
        return new Customer(
                document.getObjectId("_id"),
                document.getString("email"),
                document.getString("password"),
                waterboxes
        );
    }

}
