package com.example.EcoAqua.services;

import com.example.EcoAqua.Daos.CustomerDao;
import com.example.EcoAqua.Daos.WaterBoxDao;
import com.example.EcoAqua.documentMappers.CustomerMapper;
import com.example.EcoAqua.documentMappers.WaterBoxMapper;
import com.example.EcoAqua.models.Customer;
import com.example.EcoAqua.models.WaterBox;
import org.bson.types.ObjectId;

public class CustomerService {
    public static Customer getCustomerById(String id){
        try
        {
        return CustomerMapper.documentToCustomer(CustomerDao.getCustomer(new ObjectId(id)));
        }
        catch (IllegalArgumentException e1)
        {
        System.err.println("Bad request - getCustomer()");
        return null;
        }
        catch (Exception e)
        {
        System.err.println("No customers found with the passed id - getCustomer()");
        return null;
        }
    }
    public static Customer getCustomerByEmail(String email){
        try
        {
            return CustomerMapper.documentToCustomer(CustomerDao.getCustomer(email));
        }
        catch (IllegalArgumentException e1)
        {
            System.err.println("Bad request - getCustomer()");
            return null;
        }
        catch (Exception e)
        {
            System.err.println("No customers found with the passed email - getCustomer()");
            return null;
        }
    }

    public static void attachWaterBox(String customerId, String waterBoxId) {
        CustomerDao.attachWaterBox(new ObjectId(customerId), new ObjectId(waterBoxId));
    }

    public static WaterBox getLastWaterBox(String email) {
        try
        {
        ObjectId id = new ObjectId(CustomerDao.getLastWaterBox(email));
        return WaterBoxMapper.documentToWaterBox(WaterBoxDao.getWaterBox(id));
        }
        catch (NullPointerException e1)
        {
        System.err.println("No customers found with the passed email - getLastWaterBox()");
        return null;
        }
    }

}
