package com.example.EcoAqua.Daos;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Aggregates;
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

    public static ObjectId insertWaterBox(Document waterBox){
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
    public static void insertMeasurement(double flow, double volume, String id){
        try
        {
        waterBoxes.findOneAndUpdate(
                //filter
                new Document("_id", new ObjectId(id)),
                //update
                push("measurements",
                new Document(
                "timestamp", System.currentTimeMillis()).append(
                "flow", flow).append(
                "volume", volume))
                );
        }
        catch(MongoException ex){
            System.err.println("Error code: "+ex.getCode());
            System.err.println("Error message: "+ex.getMessage());
            System.err.println("Measurement insertion failed! - insertMeasurement()");}
        catch (Exception e){
            System.err.println("Error message: "+ e.getMessage());
        }
    }

    public static int getStatus(ObjectId id){
        try{
        return waterBoxes.find(new Document("_id",id)).first().getInteger("status");}
        catch (NullPointerException nullPointerException){
            System.err.println("Id incorreto resgistrado no dispositivo - getStatus()");
            return 1;
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            System.err.println(e.getCause().toString());
            return 1;
        }
    }

}
