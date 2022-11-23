package com.example.EcoAqua.Daos;

import com.example.EcoAqua.models.Customer;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import org.bson.BsonArray;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class CustomerDao {
    private MongoCollection<Document> customers;

    public CustomerDao() {
        this.customers = EcoAquaDao.db.getCollection("customers");
    }
    public boolean validateCustomerId(ObjectId id){
        return true;
    }
    public Document getCustomer(ObjectId id){
        if(!validateCustomerId(id)) return null;

        List<Bson> pipeline = new ArrayList<>();
        Bson match = Aggregates.match(eq("_id", id));
        pipeline.add(match);
        Document customer = this.customers.aggregate(pipeline).first();

        return customer;
    }
    public Document getCustomer(String email){

        List<Bson> pipeline = new ArrayList<>();
        Bson match = Aggregates.match(eq("email", email));
        pipeline.add(match);
        Document customer = this.customers.aggregate(pipeline).first();

        return customer;
    }

    public void signUp(String email,String password,String name) throws Exception{
        if (this.getCustomer(email)!=null)
        this.customers.insertOne(new Document(
                "email",email).append(
                "password",password).append(
                "name",name)
        );
        else throw new Exception("Conta com email ja cadastrado");
    }

    public void attachWaterBox(ObjectId customerId, ObjectId waterBoxId){
        Document customer = this.getCustomer(customerId);
        Bson updates = Updates.combine(
                Updates.set(
                        "registeredDevices."+customer.get("registeredDevices", ArrayList.class).size(),
                        waterBoxId));
        this.customers.updateOne(new Document("_id",customer.getObjectId("_id")),updates,new UpdateOptions().upsert(true));
    }
    public String getLastWaterBox(String email){
        ArrayList<ObjectId> waterBoxes = null;
        try{
        waterBoxes = this.customers.find(eq("email",email)).first().get("registeredDevices",ArrayList.class);
        System.out.println(waterBoxes.get(waterBoxes.size()-1));}
        catch (Exception e){
            System.err.println(e.getMessage());
        }
        try{
            assert waterBoxes != null;
            return waterBoxes.get(waterBoxes.size()-1).toString();}
        catch (Exception e){
            System.err.println(e.getMessage());
        }
        return "nope";
    }
    /*
    //A função a baixo não funciona em clusters M0 :(

    public void createUser(String user, String password){
        final BasicDBObject createUserCommand = new BasicDBObject("createUser", user).append("pwd", password).append("roles",
                Collections.singletonList(new BasicDBObject("role", "influxIotCustomer").append("db", "EcoAqua")));
        this.db.runCommand(createUserCommand);
    } */

    public ObjectId signIn(String email,String password) throws Exception{
        try{
        return this.customers.find(new Document(
                "email",email).append(
                "password",password)
        ).first().getObjectId("_id");}
        catch (Exception e){
            throw new Exception("Informações erradas!");
        }
    }


}
