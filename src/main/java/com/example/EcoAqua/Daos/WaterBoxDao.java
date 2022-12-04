package com.example.EcoAqua.Daos;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Updates;
import org.bson.*;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.push;

public class WaterBoxDao{

    private static final MongoCollection<Document> waterBoxes;
    static
    {
        waterBoxes = EcoAquaDao.db.getCollection("waterboxes");
    }
    public static boolean validateWaterBoxId(ObjectId id){
        Document d = waterBoxes.find(eq("_id", id)).first();
        System.out.println(d);
        return d != null;
    }

    public static Document getWaterBox(ObjectId id){
        if (!validateWaterBoxId(id)) {
            return null;
        }

        List<Bson> pipeline = new ArrayList<>();
        Bson match = Aggregates.match(eq("_id", id));
        pipeline.add(match);

        return waterBoxes.aggregate(pipeline).first();
    }

    public static ObjectId insertWaterBox(Document waterBox) {
        try
        {
        return waterBoxes.insertOne(waterBox).getInsertedId().asObjectId().getValue();
        }
        catch (NullPointerException e)
        {
        System.err.println(e.getMessage());
        System.err.println("WaterBox insertion failed - insertWaterBox()");
        return null;
        }
    }
    public static void insertMeasurement(Document measurement,ObjectId waterBoxId)throws Exception{
        Document x = null;
        try
        {
        x = waterBoxes.findOneAndUpdate(
                new Document("_id", waterBoxId),//filter
                push("measurements", measurement).toBsonDocument());//update

        System.out.println(x);
        }
        catch(MongoException ex)
        {
        System.err.println("Error code: "+ex.getCode());
        System.err.println("Error message: "+ex.getMessage());
        System.err.println("Measurement insertion failed! - insertMeasurement()");}
        catch (Exception e)
        {
        System.err.println("Error message: "+ e.getMessage());
        }
        if (x==null) throw new Exception("find failed!");
    }

    public static int getStatus(ObjectId id){
        try{
        return waterBoxes.find(new Document("_id",id)).first().getInteger("status");
        }
        catch (NullPointerException nullPointerException)
        {
        System.err.println("Id incorreto resgistrado no dispositivo - getStatus()");
        return 1;
        }
        catch (Exception e)
        {
        System.err.println(e.getMessage());
        System.err.println(e.getCause().toString());
        return 1;
        }
    }
    public static void setStatusClosed(ObjectId id) throws Exception{
        if(!validateWaterBoxId(id)) throw new Exception("id not found!");
        try
        {
            waterBoxes.findOneAndUpdate(new Document("_id", id), Updates.set("status",1).toBsonDocument());
        }
        catch (Exception e){
            System.err.println("Status couldnt be changed - setStatusClosed");
            System.err.println(e.getMessage());
        }
    }

    public static void setStatusOpened(ObjectId id) throws Exception{
        if(!validateWaterBoxId(id)) throw new Exception("id not found!");
        try
        {
            waterBoxes.findOneAndUpdate(new Document("_id", id), Updates.set("status",0).toBsonDocument());
        }
        catch (Exception e){
            System.err.println("Status couldnt be changed - setStatusOpened");
            System.err.println(e.getMessage());
        }
    }


}
