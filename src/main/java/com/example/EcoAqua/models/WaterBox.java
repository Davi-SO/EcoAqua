package com.example.EcoAqua.models;

import org.bson.BsonDateTime;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class WaterBox {
    private final ObjectId id;
    private String name;

    private final int batch;
    private byte status;

    @Override
    public String toString() {
        return "WaterBox{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", batch=" + batch +
                ", status=" + status +
                ", measurements=" + measurements +
                '}';
    }

    private ArrayList<Measurement> measurements;

    public WaterBox(ObjectId id, String name, int batch, byte status, ArrayList<Measurement> measurements) {
        this.id = id;
        this.name = name;
        this.batch = batch;
        this.status = status;
        this.measurements = measurements;
    }


    public ObjectId getId() {
        return id;
    }
    public int getBatch() {
        return batch;
    }
    public String getName() {
        return this.name;
    }

    public void setNome(String name) {
        this.name = name;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public ArrayList<Measurement> getMeasurements() {
        return measurements;
    }
    public void open(){

    }
    public void close(){
    }
    public void connect(String customerId){

    }
}

