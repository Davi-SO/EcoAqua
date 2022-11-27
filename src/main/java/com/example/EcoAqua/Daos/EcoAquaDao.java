package com.example.EcoAqua.Daos;

import com.mongodb.ConnectionString;
import com.mongodb.MongoSocketOpenException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import static java.security.Security.getProperty;

public class EcoAquaDao {
    protected static final String connectionString = "mongodb+srv://davi-so:qwerty12345@ecoaqua.pa1g3ed.mongodb.net/?retryWrites=true&w=majority";
    protected static MongoClient mongoClient;
    protected static MongoDatabase db;
    static {

        mongoClient = MongoClients.create(new ConnectionString(connectionString));
        db = mongoClient.getDatabase("EcoAqua");
    }

}
