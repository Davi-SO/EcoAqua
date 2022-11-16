package com.example.EcoAqua.Daos;

import com.example.EcoAqua.models.WaterBox;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import org.bson.*;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class WaterBoxDao extends AbstractEcoAquaDao{

    private final MongoCollection<Document> waterBoxes;
    public WaterBoxDao(String user, String password) {
        super(user, password);
        this.waterBoxes = this.db.getCollection("waterboxes");
    }
    public boolean validateWaterBoxId(ObjectId id){
        return true;
    }

    public Document getWaterBox(ObjectId id){
        if (!validateWaterBoxId(id)) {
            return null;
        }

        List<Bson> pipeline = new ArrayList<>();
        Bson match = Aggregates.match(Filters.eq("_id", id));
        pipeline.add(match);
        Document waterBox = this.waterBoxes.aggregate(pipeline).first();

        return waterBox;
    }

    public String insertWaterBox(Document waterBox){
           return this.waterBoxes.insertOne(waterBox).getInsertedId().asObjectId().getValue().toString();
    }


}
