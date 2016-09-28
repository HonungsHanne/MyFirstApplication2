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
        ch.updateColor(model);
        String [] Test = new String[3];



    }
}
