package com.example.EcoAqua.services;

import com.example.EcoAqua.documentMappers.WaterBoxMapper;
import com.example.EcoAqua.models.Measurement;
import com.example.EcoAqua.models.WaterBox;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static com.example.EcoAqua.services.WaterBoxService.*;
import static org.junit.jupiter.api.Assertions.*;

class WaterBoxServiceTest {

    @Test
    void checkInfoFail() {
        String idIncorrect1 = "";
        String idIncorrect2 = "that's all folks";
        String idIncorrect3 = "asdf5123afaserttryu54215";
        String idIncorrect4 = "63741944cf5fa954d5264b4d";

        assertFalse(checkInfo(idIncorrect1));
        assertFalse(checkInfo(idIncorrect2));
        assertFalse(checkInfo(idIncorrect3));
        assertFalse(checkInfo(idIncorrect4));
    }

    @Test
    void checkInfoSuccess() {
        String devicesExist = "6381371888a2a3328083cc26";

        assertTrue(checkInfo(devicesExist));
    }

    @Test
    void getStatusFail() {
        String idIncorrect1 = "";
        String idIncorrect2 = "that's all folks";
        String idIncorrect3 = "asdf5123afaserttryu54215";
        String idIncorrect4 = "63741944cf5fa954d5264b4d";

        assertEquals("1", getStatus(idIncorrect1));
        assertEquals("1", getStatus(idIncorrect2));
        assertEquals("1", getStatus(idIncorrect3));
        assertEquals("1", getStatus(idIncorrect4));
    }

    @Test
    void getStatusSuccess() {
        String idCorrect = "6381371888a2a3328083cc26";

        String status = getStatus(idCorrect);

        assertEquals("0", status);

        System.out.println("Water Box Status: " + status);
    }

    @Test
    void getWaterBoxFail() {
        String idIncorrect1 = "";
        String idIncorrect2 = "that's all folks";
        String idIncorrect3 = "asdf5123afaserttryu54215";
        String idIncorrect4 = "63741944cf5fa954d5264b4d";


        assertNull(getWaterBox(idIncorrect1));
        assertNull(getWaterBox(idIncorrect2));
        assertNull(getWaterBox(idIncorrect3));
        assertNull(getWaterBox(idIncorrect4));

    }

    //TODO: Rodar o m??todo e depois fazer uma busca pelo valor inserido/criado
    @Test
    void getWaterBoxSuccess() {
        String idCorrect = "6381371888a2a3328083cc26";


    }

    //TODO: Rodar o m??todo e depois fazer uma busca pelo valor inserido/criado
    @Test
    void insertMeasurementFail() {
        Measurement medida = new Measurement(123456789, 10.5, 23.4);
        String idFail1 = "63741944cf5fa954d5264b4d";

    }

    //TODO: Como testar um m??todo void
    @Test
    void insertMeasurementSuccess() {
    }

    //TODO: fun????o n??o est?? pronta
    @Test
    void insertWaterBox() {
    }
}