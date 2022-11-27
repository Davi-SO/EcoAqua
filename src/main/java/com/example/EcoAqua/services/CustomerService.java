package com.example.EcoAqua.services;

import com.example.EcoAqua.Daos.CustomerDao;
import com.example.EcoAqua.Daos.WaterBoxDao;
import com.example.EcoAqua.documentMappers.CustomerMapper;
import com.example.EcoAqua.documentMappers.WaterBoxMapper;
import com.example.EcoAqua.models.Customer;
import com.example.EcoAqua.models.WaterBox;
import org.bson.types.ObjectId;

import java.util.regex.Pattern;

public class CustomerService {
    public static Customer getCustomerById(String id){
        try
        {
            return CustomerMapper.documentToCustomer(CustomerDao.getCustomer(new ObjectId(id)));
        }
        catch (IllegalArgumentException e1)
        {
            System.err.println("Bad request - Invalid ObjectId for Customer");
            return null;
        }
        catch (Exception e)
        {
            System.err.println("No customers found with this id!");
            return null;
        }
    }
    public static Customer getCustomerByEmail(String email){
        if(!isValid(email)){
            System.err.println("Invalid email");
            return null;
        }

        try
        {
            return CustomerMapper.documentToCustomer(CustomerDao.getCustomer(email));
        }
        catch (IllegalArgumentException e1)
        {
            System.err.println("Bad request - Invalid Parameter");
            return null;
        }
        catch (Exception e)
        {
            System.err.println("No customers found with this email!");

            return null;
        }
    }

    public static void attachWaterBox(String customerId, String waterBoxId) {
        CustomerDao.attachWaterBox(new ObjectId(customerId), new ObjectId(waterBoxId));
    }

    public static WaterBox getLastWaterBox(String email) {
        if(!isValid(email)){
            System.err.println("Email invalido");
            return null;
        }

        try
        {
            ObjectId id = new ObjectId(CustomerDao.getLastWaterBox(email));
            return WaterBoxMapper.documentToWaterBox(WaterBoxDao.getWaterBox(id));
        }
        catch (NullPointerException e1)
        {
            System.err.println("No customers found with this email for the WaterBox");
            return null;
        }
    }

    private static boolean isValid(String email){
        String mailRegex =  "^[A-z0-9_+&*-]+(?:\\.[A-z0-9_+&*-]+)*@(?:[A-z0-9-]+\\.)+[A-z]{2,7}$";

        Pattern pattern = Pattern.compile(mailRegex);

        if(email == null)
            return false;

        return pattern.matcher(email).matches();
    }

}
