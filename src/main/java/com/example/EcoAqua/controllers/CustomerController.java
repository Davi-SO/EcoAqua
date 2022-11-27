package com.example.EcoAqua.controllers;

import com.example.EcoAqua.Daos.CustomerDao;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/influxIOT")
public class CustomerController {
    @PostMapping(value="/sign-up")
    public void signUp(@RequestBody String payload){
        /*
        payload:{
            name:String,
            email:String,
            password:String
        }
         */
        CustomerDao customerDao = new CustomerDao();
        Map<String, Object> data = null;
        try{
            System.out.println(payload);
            data = new ObjectMapper().readValue(payload, Map.class);
            System.out.println(data.toString());
            System.out.println(data.get("name").toString());
        }
        catch (Exception e ){
            System.err.println(e.getMessage());
        }
        try{
            customerDao.signUp((String) data.get("name"), (String) data.get("email"), (String) data.get("password"));
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
    @PostMapping(value="/signIn")
    public String signIn(@RequestBody String payload){
        CustomerDao customerDao = new CustomerDao();
        Map<String, Object> data = null;
        String id = null;
        try{
            data = new ObjectMapper().readValue(payload, HashMap.class);}
        catch (Exception e ){
            System.err.println(e.getMessage());
        }
        return id;
    }
}
