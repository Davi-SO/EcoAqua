package com.example.EcoAqua.models;

import org.bson.BsonDateTime;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class WaterBox {
    private ObjectId id;
    private String nome;
    private byte status;
    private ArrayList<Measurement> measurements;

    public WaterBox(){

    }

    public ObjectId getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

