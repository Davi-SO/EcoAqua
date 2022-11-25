package com.example.EcoAqua.documentMappers;

import com.example.EcoAqua.models.Measurement;
import com.example.EcoAqua.models.WaterBox;
import org.bson.Document;
import java.util.ArrayList;

public class WaterBoxMapper {
    public static Document waterBoxToDocument(WaterBox waterBox){
        ArrayList<Document> measurements = new ArrayList<>();
        for(Measurement m:waterBox.getMeasurements()){
            measurements.add(MeasurementMapper.measurementToDocument(m));
        }
        return new Document(
                "_id", waterBox.getId()).append(
                "name",waterBox.getName()).append(
                "batch",waterBox.getBatch()).append(
                "status",waterBox.getStatus()).append(
                "measurements",measurements
        );
    }
    public static WaterBox documentToWaterBox(Document document){
        ArrayList<Measurement> measurements = new ArrayList<>();
        for(Object d:document.get("measurements",ArrayList.class)){
            try{
            measurements.add(MeasurementMapper.documentToMeasurement((Document)d));
            }catch (Exception e){
                System.err.println(e.getMessage());
            }
        }
        return new WaterBox(
                document.getObjectId("_id"),
                document.get("name").toString(),
                document.getInteger("batch"),
                document.getInteger("status").byteValue(),
                measurements
        );
    }
}
