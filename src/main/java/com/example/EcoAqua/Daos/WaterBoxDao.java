package com.example.EcoAqua.Daos;

import com.example.EcoAqua.models.WaterBox;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import org.bson.*;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Aggregates.set;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.push;

public class WaterBoxDao extends AbstractEcoAquaDao{

    private final MongoCollection<Document> waterBoxes;
    public WaterBoxDao(String user, String password) {
        super(user, password);
        this.waterBoxes = this.db.getCollection("waterboxes");
    }
    public boolean validateWaterBoxId(ObjectId id){
        return this.waterBoxes.find(eq("_id", id)).first() != null;
    }

    public Document getWaterBox(ObjectId id){
        if (!validateWaterBoxId(id)) {
            return null;
        }

        List<Bson> pipeline = new ArrayList<>();
        Bson match = Aggregates.match(eq("_id", id));
        pipeline.add(match);
        Document waterBox = this.waterBoxes.aggregate(pipeline).first();

        return waterBox;
    }

    public String insertWaterBox(Document waterBox){
           return this.waterBoxes.insertOne(waterBox).getInsertedId().asObjectId().getValue().toString();
    }
    public String insertMeasurement(double flow,double volume,String id){
        Document document = null;
        try{
            document =
        this.waterBoxes.findOneAndUpdate(new Document(
                //filter
                "_id", new ObjectId(id)),
                //update
                push("measurements",
                new Document(
                "timestamp", System.currentTimeMillis()).append(
                "flow", flow).append(
                "volume", volume)));}
        catch(MongoException ex){
            System.err.println("Error code: "+ex.getCode());
            System.err.println("Error message: "+ex.getMessage());}
        catch (Exception e){
            System.err.println("Error message: "+ e.getMessage());
        }
        return document.toJson();
    }

    public int getStatus(String id){
        return this.waterBoxes.find(new Document("_id",new ObjectId(id))).first().getInteger("status");
    }

}
