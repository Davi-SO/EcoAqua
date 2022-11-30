package com.example.EcoAqua.Daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class CustomerDao {
    private static final MongoCollection<Document> customers;

    static
    {
        customers = EcoAquaDao.db.getCollection("customers");
    }
    public static boolean validateCustomerId(ObjectId id){
        return customers.find(new Document("_id",id)).first()!=null;
    }
    public static Document getCustomer(ObjectId id){
        if(!validateCustomerId(id)) return null;
        List<Bson> pipeline = new ArrayList<>();
        Bson match = Aggregates.match(eq("_id", id));
        pipeline.add(match);
        return customers.aggregate(pipeline).first();
    }
    public static Document getCustomer(String email){

        List<Bson> pipeline = new ArrayList<>();
        Bson match = Aggregates.match(eq("email", email));
        pipeline.add(match);
        return customers.aggregate(pipeline).first();
    }

    public void signUp(String name,String email,String password) throws Exception{
        if (getCustomer(email)==null){
        customers.insertOne(new Document(
                "email",email).append(
                "password",password).append(
                "name",name).append(
                "registeredDevices",new ArrayList<>()
                )
        );
        }
        else {throw new Exception("Conta com email ja cadastrado");}
    }


    public static void attachWaterBox(ObjectId customerId, ObjectId waterBoxId){
        Document customer = getCustomer(customerId);
        Bson updates = Updates.combine(
                Updates.set(
                        "registeredDevices."+customer.get("registeredDevices", ArrayList.class).size(),
                        waterBoxId));
        customers.updateOne(new Document("_id",customer.getObjectId("_id")),updates,new UpdateOptions().upsert(true));
    }
    public static String getLastWaterBox(String email){
        ArrayList<ObjectId> waterBoxes = null;
        try{
        waterBoxes = customers.find(eq("email",email)).first().get("registeredDevices",ArrayList.class);
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
        return "fail!";
    }
    /*
    //A função a baixo não funciona em clusters M0 :(

    public void createUser(String user, String password){
        final BasicDBObject createUserCommand = new BasicDBObject("createUser", user).append("pwd", password).append("roles",
                Collections.singletonList(new BasicDBObject("role", "influxIotCustomer").append("db", "EcoAqua")));
        this.db.runCommand(createUserCommand);
    } */




}
