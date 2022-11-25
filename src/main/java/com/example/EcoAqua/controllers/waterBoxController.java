package com.example.EcoAqua.controllers;

import com.example.EcoAqua.Daos.CustomerDao;
import com.example.EcoAqua.Daos.WaterBoxDao;
import com.example.EcoAqua.documentMappers.WaterBoxMapper;
import com.example.EcoAqua.services.CustomerService;
import com.example.EcoAqua.services.WaterBoxService;
import org.bson.Document;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/influxIOT/waterbox")
public class WaterBoxController {

    @PostMapping(value="/checkInfo")
    public boolean checkInfo(@RequestBody String payload){
        Map<String, Object> data = null;
        try
        {
            data = new ObjectMapper().readValue(payload, Map.class);
            return WaterBoxService.checkInfo(data.get("id").toString());
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
            return false;
        }
    }
    @PostMapping(value = "/status")
    public String getStatus(@RequestBody String payload){
        Map<String, Object> data = null;
        try
        {
        data = new ObjectMapper().readValue(payload, Map.class);
        }
        catch (Exception e)
        {
        System.err.println(e.getMessage());
        return String.valueOf(1);
        }
        System.out.println(WaterBoxService.getStatus(data.get("id").toString()));
        return WaterBoxService.getStatus(data.get("id").toString());
    }
    @PostMapping(value = "/measurements")
    public void updateMeasurements(@RequestBody String  payload){
        Map<String, Object> data = null;
        try
        {
        data = new ObjectMapper().readValue(payload, Map.class);
        }
        catch (Exception e )
        {
        System.err.println("The WaterBox device sent a problematic request - updateMeasurements()");
        System.err.println(e.getMessage());
        }
        try
        {
        WaterBoxDao.insertMeasurement((double)data.get("flow"),(double)data.get("volume"),data.get("id").toString());
        }
        catch (Exception e)
        {
        System.err.println("bad function call - updateMeasurements()");
        System.err.println(e.getMessage());
        }
    }
    @PostMapping(value="/insertWaterBox")
    public String insertWaterBox(@RequestBody String payload) {
        Map<String, Object> data = null;
        System.out.println(payload);
        try
        {
        data = new ObjectMapper().readValue(payload, HashMap.class);
        }
        catch (Exception e )
        {
        System.err.println(e.getMessage());
        System.err.println("The WaterBox device sent a problematic request - insertWaterBox()");
        }
//        try{
        CustomerService.attachWaterBox(
                //customer id
                String.valueOf(CustomerDao.getCustomer(data.get("email").toString()).getObjectId("_id")),
                //box id
                String.valueOf(WaterBoxDao.insertWaterBox(
                    new Document(
                    "name",data.get("name")).append(
                    "batch",(int)data.get("batch")).append(
                    "status",0)
                ))
        );
        return CustomerService.getLastWaterBox(data.get("email").toString()).getId().toHexString();
    }
}
