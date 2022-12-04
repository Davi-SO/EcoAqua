package com.example.EcoAqua.controllers;

import com.example.EcoAqua.Daos.CustomerDao;
import com.example.EcoAqua.Daos.WaterBoxDao;
import com.example.EcoAqua.documentMappers.WaterBoxMapper;
import com.example.EcoAqua.models.Measurement;
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
    @PostMapping(value = "/close")
    public boolean close(@RequestBody String payload){
        /*payload
        {"id": String}
         */
        Map<String, Object> data = null;
        System.out.println(payload);
        try
        {
            data = new ObjectMapper().readValue(payload, Map.class);
        }
        catch (Exception e)
        {
            System.err.println("Bad request - close()");
            System.err.println(e.getMessage());
            return false;
        }
        return WaterBoxService.setStatusClosed(data.get("id").toString());
    }
    @PostMapping(value = "/measurements")
    public void updateMeasurements(@RequestBody String  payload){
        HashMap<String, Object> data = null;
        Measurement m = null;
        System.out.println(payload);
        try
        {
        data = new ObjectMapper().readValue(payload, HashMap.class);
        }
        catch (Exception e )
        {
        System.err.println("The WaterBox device sent a problematic request - updateMeasurements()");
        System.err.println(e.getMessage());
        return;
        }
        try
        {
        m = new Measurement(System.currentTimeMillis(),(double)data.get("flow"),(double)data.get("volume"));
        }
        catch (Exception e)
        {
        System.err.println("bad function call - updateMeasurements()");
        System.err.println(e.getMessage());
        return;
        }
        try
        {
            WaterBoxService.insertMeasurement(m,data.get("id").toString());
        }
        catch (Exception e){
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
        return "fail!";
        }

        CustomerService.attachWaterBox(
                //customer id
                String.valueOf(CustomerService.getCustomerByEmail(data.get("email").toString())),
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
    @PostMapping(value = "/volume")
    public double getVolume(@RequestBody String payload){
        Map<String, Object> data = null;
        try
        {
            data = new ObjectMapper().readValue(payload, Map.class);
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
            return 0;
        }
        return WaterBoxService.getVolume(data.get("id").toString());
    }
}
