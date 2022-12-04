package com.example.EcoAqua.services;

import com.example.EcoAqua.documentMappers.WaterBoxMapper;
import com.example.EcoAqua.models.Customer;
import org.junit.jupiter.api.Test;
import com.example.EcoAqua.services.CustomerService;
import org.springframework.util.Assert;

import static com.example.EcoAqua.services.CustomerService.*;
import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest{

    @Test
    void getCustomerByIdNull() {
        String idIncorrect1 = "1";
        String idIncorrect2 = "63741944cf5fa954d5264b4d";

        assertNull(getCustomerById(idIncorrect1));
        assertNull(getCustomerById(idIncorrect2));

    }

    @Test
    void getCustomerByIdSuccess(){
        String idCorrect = "63741944cf5fa954d5264b4c";

        Customer first = getCustomerById(idCorrect);

        assertNotNull(first);

        System.out.println(first);
        System.out.println("Customer Found Above!");
    }

    @Test
    void getCustomerByEmailNull() {
        String emailIncorrect = "varewel";
        String emailIncorrect2 = " ";
        String emailIncorrect3 = "influx@iot.com";

        assertNull(getCustomerByEmail(emailIncorrect));
        assertNull(getCustomerByEmail(emailIncorrect2));
        assertNull(getCustomerByEmail(emailIncorrect3));
    }

    @Test
    void getCustomerByEmailSuccess() {
        String emailCorrect = "davi-so@puccampinas.edu.br";

        Customer foundedCustomer = getCustomerByEmail(emailCorrect);

        assertNotNull(foundedCustomer);

        System.out.println(foundedCustomer);
        System.out.println("Customer Found Above!");
    }

    @Test
    void getLastWaterBoxNull() {
        String emailIncorrect = "farewell";
        String emailIncorrect2 = " ";

        assertNull(getLastWaterBox(emailIncorrect));
        assertNull(getLastWaterBox(emailIncorrect2));
    }

    @Test
    void getLastWaterBoxSuccess() {
        String emailCorrect = "davi-so@puccampinas.edu.br";

        assertNotNull(getLastWaterBox(emailCorrect));

        System.out.println(emailCorrect);
        System.out.println("Customer Found Above!");
    }

}