package com.example.hanne.myfirstapp;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

/**
 * Created by Hanne on 2017-03-26.
 */

public class AccelerometerActivity extends Activity implements SensorEventListener {
    private SensorManager mySensorManager;
    private Sensor myAccelerometer;


    @Override
    public void onCreate(Bundle b1){
    super.onCreate(b1);
        setContentView(R.layout.activity_accelerometer);
        mySensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        myAccelerometer = mySensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mySensorManager.registerListener(this, myAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);

    }

    public void onResume(){
        super.onResume();
        mySensorManager.registerListener(this, myAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }
    public void onPause(){
        super.onPause();
        mySensorManager.unregisterListener(this);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){

    }
}

