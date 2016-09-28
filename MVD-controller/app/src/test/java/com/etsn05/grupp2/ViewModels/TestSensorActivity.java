package com.etsn05.grupp2.ViewModels;

import org.junit.After;
import org.junit.Before;

/**
 * Created by micke on 2016-09-28.
 */
public class TestSensorActivity {
    private SensorActivity sensor;

    @Before
    public void onStart(){
        sensor = new SensorActivity();
    }
    @After
    public void onEnd(){
        sensor = null;
    }
    public void testOnClickTemperature(){

    }
    public void testOnClickPressure(){

    }
    public void testOnClickHumidity(){

    }
    public void testOnClickGyroscope(){

    }
    public void testOnClickAccelerometer(){

    }
    public void testOnClickMagnometer(){

    }

}