package com.example.EcoAqua;

import com.example.EcoAqua.Daos.CustomerDao;
import com.example.EcoAqua.Daos.WaterBoxDao;
import com.example.EcoAqua.documentMappers.CustomerMapper;
import com.example.EcoAqua.documentMappers.MeasurementMapper;
import com.example.EcoAqua.documentMappers.WaterBoxMapper;
import com.example.EcoAqua.models.Customer;
import com.example.EcoAqua.models.WaterBox;
import org.bson.types.ObjectId;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.bson.Document;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class})
public class EcoaquaApplication {
	public static void main(String[] args) {
		try {
			System.setProperty("spring.devtools.restart.enabled", "false");
			SpringApplication.run(EcoaquaApplication.class, args);
		}catch (Exception e){
			System.err.println(e.getMessage());
			System.err.println(e.getCause());
			System.err.println(e.getClass());
		}

	}
}
