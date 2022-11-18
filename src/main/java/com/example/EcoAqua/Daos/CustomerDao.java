package com.example.EcoAqua.Daos;

import com.example.EcoAqua.models.Customer;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import org.bson.BsonArray;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class CustomerDao extends AbstractEcoAquaDao{
    private MongoCollection<Document> customers;

    public CustomerDao(String user, String password) {
        super(user, password);
        this.customers = this.db.getCollection("customers");
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

    public void signUpCustomer(Document customer){
    }

    public void attachWaterBox(Document customer, Document waterBox){
        WaterBoxDao waterBoxDao = new WaterBoxDao(this.user,this.password);
        Bson updates = Updates.combine(
                Updates.set(
                        "registeredDevices."+customer.get("registeredDevices", ArrayList.class).size(),
                        waterBoxDao.insertWaterBox(waterBox)));
        this.customers.updateOne(new Document("_id",customer.getObjectId("_id")),updates,new UpdateOptions().upsert(true));
    }
    public String getLastWaterBox(String email){
        ArrayList<String> waterBoxes = this.customers.find(eq("email",email)).first().get("registeredDevices",ArrayList.class);
        System.out.println(waterBoxes.get(waterBoxes.size()-1));
        return waterBoxes.get(waterBoxes.size()-1);
    }

}
