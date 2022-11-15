package com.example.EcoAqua;

import com.example.EcoAqua.Daos.WaterBoxDao;
import com.example.EcoAqua.models.WaterBox;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcoaquaApplication {
	public static void main(String[] args) {
		SpringApplication.run(EcoaquaApplication.class, args);
		WaterBoxDao wb = new WaterBoxDao("davi-so","qwerty12345");

	}
}
