package com.example.EcoAqua.Daos;

import org.bson.*;

public class WaterBoxDao extends AbstractEcoAquaDao{

    public WaterBoxDao(String user, String password) {
        super(user, password);
    }
    public void getCollections(){
        String ds[];
        for (Document d: this.db.listCollections()){
            System.out.println(d.toJson().toString());
        }
    }
}
