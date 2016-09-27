package com.etsn05.grupp2.ViewModels;

import org.junit.Test;

import Connection.ConnectionHandler;
import Model.SensorModel;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testGetTemperature() {
        SensorModel m = new SensorModel("70", "WICED Sense2 Kit", "00:10:18:01:92:20", "1");
        ConnectionHandler c = new ConnectionHandler();
        if(m.temperature == null) System.out.println("WOOH");
        c.updateTemp(m);
        System.out.println(m.temperature);
    }
}