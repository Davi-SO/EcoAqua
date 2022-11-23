package com.example.EcoAqua;

import com.example.EcoAqua.Daos.CustomerDao;
import com.example.EcoAqua.Daos.WaterBoxDao;
import com.example.EcoAqua.documentMappers.CustomerMapper;
import com.example.EcoAqua.models.Customer;
import com.example.EcoAqua.models.WaterBox;
import org.bson.types.ObjectId;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.bson.Document;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class EcoaquaApplication {
	public static void main(String[] args) {
		SpringApplication.run(EcoaquaApplication.class, args);}
}
