package com.example.EcoAqua.documentMappers;

import com.example.EcoAqua.models.Measurement;
import org.bson.Document;
public class MeasurementMapper {
    public static Document measurementToDocument(Measurement measurement){
        return new Document(
                "timestamp",measurement.getTimestamp()).append(
                "flow",measurement.getFlow()).append(
                "volume",measurement.getVolume()
        );
    }
    public static Measurement documentToMeasurement(Document document){
        return new Measurement(
                document.getLong("timestamp"),
                document.getDouble("flow"),
                document.getDouble("volume")
        );
    }
}
