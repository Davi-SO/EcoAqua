package com.example.EcoAqua.services;

import com.example.EcoAqua.Daos.WaterBoxDao;
import com.example.EcoAqua.documentMappers.MeasurementMapper;
import com.example.EcoAqua.documentMappers.WaterBoxMapper;
import com.example.EcoAqua.models.Measurement;
import com.example.EcoAqua.models.WaterBox;
import org.bson.types.ObjectId;
import org.bson.Document;

public class WaterBoxService {
    public static boolean checkInfo(String id){
        try
        {
        return WaterBoxDao.validateWaterBoxId(new ObjectId(id));
        }
        catch (Exception e){
            if (e.getClass()==IllegalArgumentException.class){
            System.err.println("The WaterBox device sent a problematic request - checkInfo()");
            }
            return false;
        }
    }
    public static String getStatus(String id) {
        try
        {
        return String.valueOf(WaterBoxDao.getStatus(new ObjectId(id)));
        }
        catch (Exception e)
        {
        System.err.println(e.getMessage());
        System.err.println("The WaterBox device sent a problematic request - checkStatus()");
        return String.valueOf(1);
        }
    }
    public static boolean setStatusClosed(String id){
        ObjectId oid = null;
        try
        {
        oid = new ObjectId(id);
        }
        catch (Exception e){
            System.err.println("Bad request - setStatusClosed()");
            System.err.println(e.getMessage());
        }
        try
        {
        WaterBoxDao.setStatusClosed(oid);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }
    public WaterBox getWaterBox(String id){
        try
        {
       return WaterBoxMapper.documentToWaterBox(WaterBoxDao.getWaterBox(new ObjectId(id)));
        }

        catch (IllegalArgumentException e1)
        {
        System.err.println("The WaterBox device sent a problematic request - getWaterBox()");
        return null;
        }
        catch(Exception e2)
        {
        System.err.println("No waterBoxes found with the id passed - getWaterBox()");
        return null;
        }
    }
    public static void insertMeasurement(Measurement m,String id){
        ObjectId oid = null;
        try
        {
            oid = new ObjectId(id);
        }
        catch (IllegalArgumentException e)
        {
            System.err.println("The WaterBox device sent a problematic request - insertMeasurement()");
            System.err.println(e.getMessage());
            return;
        }
        try
        {
        WaterBoxDao.insertMeasurement(MeasurementMapper.measurementToDocument(m),oid);
        }
        catch (Exception e){
            System.err.println("The id provided found no devices");
            System.err.println(e.getMessage());
            return;
        }
    }
    public static void insertWaterBox(WaterBox waterBox){

    }


}
