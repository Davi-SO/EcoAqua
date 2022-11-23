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
        CustomerDao customerDao = new CustomerDao();
        Map<String, Object> data = null;
        try{
            data = new ObjectMapper().readValue(payload, HashMap.class);}
        catch (Exception e ){
            System.err.println(e.getMessage());
        }
        try{
            customerDao.signUp(data.get("email").toString(),data.get("password").toString(),data.get("name").toString());
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
    @PostMapping(value="/signIn")
    public void signIn(@RequestBody String payload){
        CustomerDao customerDao = new CustomerDao();
        Map<String, Object> data = null;
        try{
            data = new ObjectMapper().readValue(payload, HashMap.class);}
        catch (Exception e ){
            System.err.println(e.getMessage());
        }
    }
}
