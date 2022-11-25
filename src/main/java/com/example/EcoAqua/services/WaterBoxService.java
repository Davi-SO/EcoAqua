package com.example.EcoAqua.services;

import com.example.EcoAqua.Daos.WaterBoxDao;
import com.example.EcoAqua.documentMappers.WaterBoxMapper;
import com.example.EcoAqua.models.WaterBox;
import org.bson.types.ObjectId;

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


}