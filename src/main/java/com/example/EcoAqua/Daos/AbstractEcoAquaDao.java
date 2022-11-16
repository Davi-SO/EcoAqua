package com.example.EcoAqua.Daos;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import static java.security.Security.getProperty;

public abstract class AbstractEcoAquaDao {
    protected String clusterAddress = "@ecoaqua.pa1g3ed.mongodb.net/?retryWrites=true&w=majority";
    protected MongoClient client;
    protected MongoDatabase db;

    protected String user;
    protected String password;

    protected AbstractEcoAquaDao(String user,String password){
        this.user = user;
        this.password = password;
        this.client = MongoClients.create(new ConnectionString("mongodb+srv://" +user+":"+password+clusterAddress));
        this.db = this.client.getDatabase("EcoAqua");
    }
}
