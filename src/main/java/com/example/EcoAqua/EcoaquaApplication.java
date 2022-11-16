package com.example.EcoAqua;

import com.example.EcoAqua.Daos.CustomerDao;
import com.example.EcoAqua.Daos.WaterBoxDao;
import com.example.EcoAqua.models.WaterBox;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class EcoaquaApplication {
	public static void main(String[] args) {
		SpringApplication.run(EcoaquaApplication.class, args);}
}
