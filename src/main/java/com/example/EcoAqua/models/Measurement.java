package com.example.EcoAqua.models;

import org.bson.BsonDateTime;

public class Measurement {
    private long timestamp;
    private double flow;
    private double volume;

    public Measurement(long timestamp, double flow, double volume) {
        this.timestamp = timestamp;
        this.flow = flow;
        this.volume = volume;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public double getFlow() {
        return flow;
    }

    public double getVolume() {
        return volume;
    }
}
