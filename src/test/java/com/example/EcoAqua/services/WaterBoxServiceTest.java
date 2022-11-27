package com.example.EcoAqua.services;

import com.example.EcoAqua.models.Measurement;
import com.example.EcoAqua.models.WaterBox;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;

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

    //TODO: Ver com o maligno pq está chamando do WaterBoxDao e não dos Services
    @Test
    void getWaterBoxFail() {
    }

    //TODO: Ver com o maligno pq está chamando do WaterBoxDao e não dos Services
    @Test
    void getWaterBoxSuccess() {
    }

    //TODO: Como testar um método void
    @Test
    void insertMeasurementFail() {
        Measurement medida = new Measurement(123456789, 10.5, 23.4);
        String idFail1 = "63741944cf5fa954d5264b4d";

    }

    //TODO: Como testar um método void
    @Test
    void insertMeasurementSuccess() {
    }

    //TODO: função não está pronta
    @Test
    void insertWaterBox() {
    }
}