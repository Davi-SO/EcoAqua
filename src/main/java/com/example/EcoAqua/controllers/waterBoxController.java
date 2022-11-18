package com.example.EcoAqua.controllers;

import com.example.EcoAqua.Daos.CustomerDao;
import com.example.EcoAqua.Daos.WaterBoxDao;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.apache.tomcat.util.json.JSONParser;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/influxIOT/waterbox")
public class waterBoxController {
    @GetMapping(value = "/connect/{email}")
    public String getWaterBoxId(@PathVariable("email")String email){
        CustomerDao customerDao = new CustomerDao("davi-so","qwerty12345");
        return customerDao.getLastWaterBox(email);
    }
    @GetMapping(value="/checkInfo/{id}")
    public boolean checkInfo(@PathVariable("id") String id){
        WaterBoxDao waterBoxDao = new WaterBoxDao("davi-so","qwerty12345");
        try{
        return waterBoxDao.validateWaterBoxId(new ObjectId(id));}
        catch (Exception e) {return false;}
    }
    @PostMapping(value = "/{id}/measurements")
    public String updateMeasurements(@PathVariable("id")String id,@RequestBody String  payload){
        WaterBoxDao waterBoxDao = new WaterBoxDao("davi-so","qwerty12345");
        Map<String, Object> data = null;
        try{
        data = new ObjectMapper().readValue(payload, HashMap.class);}
        catch (Exception e ){
            return e.getMessage();
        }
        try{
        return waterBoxDao.insertMeasurement((double)data.get("flow"),(double)data.get("volume"),id);}
        catch (Exception e){
            return e.getMessage();
        }
    }
}
