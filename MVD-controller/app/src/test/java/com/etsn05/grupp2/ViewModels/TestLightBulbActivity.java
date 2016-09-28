package com.etsn05.grupp2.ViewModels;

import org.junit.Test;

import Connection.ConnectionHandler;
import Model.LightbulbModel;

/**
 * Created by Hanne on 2016-09-28.
 */

public class TestLightBulbActivity {
    View view;
    ConnectionHandler ch;
    LightbulbModel model;

    @Test
    public void testOnClickGetButton(View view) {
        LightbulbModel model = new LightbulbModel("70", "Lampa1", "00:10:18:01:92:20", "1");
        model.blue = "00";
        model.green = "01";
        model.red = "01";
        model.white = "00";
        String [] Test = new String[3];
        Test[0]= model.red;
        Test[1] = model.green;
        Test[2] = model.blue;
        Test[3] = model.white;

        ch.updateColor(model); //Uppdaterar modellen med nya färger

        assertArrayEquals();








    }
}
