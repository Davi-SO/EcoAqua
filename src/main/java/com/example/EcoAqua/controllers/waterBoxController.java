package com.example.EcoAqua.controllers;

import com.example.EcoAqua.Daos.CustomerDao;
import com.example.EcoAqua.Daos.WaterBoxDao;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/influxIOT/waterbox")
public class WaterBoxController {


    @PostMapping(value="/checkInfo")
    public boolean checkInfo(@RequestBody String id){
        WaterBoxDao waterBoxDao = new WaterBoxDao();
        try{
        return waterBoxDao.validateWaterBoxId(new ObjectId(id));}
        catch (Exception e) {return false;}
    }
    @PostMapping(value = "/status")
    public String getStatus(@RequestBody String id){
        WaterBoxDao waterBoxDao = new WaterBoxDao();
        return String.valueOf(waterBoxDao.getStatus(id));
    }
    @PostMapping(value = "/measurements")
    public String updateMeasurements(@RequestBody String  payload){
        WaterBoxDao waterBoxDao = new WaterBoxDao();
        Map<String, Object> data = null;
        try{
        data = new ObjectMapper().readValue(payload, HashMap.class);}
        catch (Exception e ){
            System.err.println(e.getMessage());
            return e.getMessage();
        }
        try{
        return waterBoxDao.insertMeasurement((double)data.get("flow"),(double)data.get("volume"),data.get("id").toString());}
        catch (Exception e){
            System.err.println(e.getMessage());
            return e.getMessage();
        }
    }
    @PostMapping(value="/insertWaterBox")
    public String insertWaterBox(@RequestBody String payload) throws Exception{
        //Payload:
        //{email:String
        // name: String,
        //batch: int}
        Map<String, Object> data = null;
        try{
            data = new ObjectMapper().readValue(payload, HashMap.class);}
        catch (Exception e ){
            System.err.println(e.getMessage());
            System.err.println("failed!");
            //throw new Exception("failed WaterBoxController.insertWaterBox! #0");

        }
//        try{
        WaterBoxDao waterBoxDao = new WaterBoxDao();
        CustomerDao customerDao = new CustomerDao();
        customerDao.attachWaterBox(
                //customer id
                customerDao.getCustomer(data.get("email").toString()).getObjectId("_id"),
                //box id
                new ObjectId(waterBoxDao.insertWaterBox(new Document(
                "name",data.get("name")).append(
                "batch",(int)data.get("batch")).append(
                "status",0)
                ))
        );
        return customerDao.getLastWaterBox(data.get("email").toString());
//        catch (Exception e){
//            System.err.println(e.getMessage());
//            return "failed!";
            //throw new Exception("failed WaterBoxController.insertWaterBox! #1");
//        }
    }
}
