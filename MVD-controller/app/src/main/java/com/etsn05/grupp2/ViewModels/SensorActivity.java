package com.etsn05.grupp2.ViewModels;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SensorActivity extends AppCompatActivity {
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        this.t = (TextView) findViewById(R.id.textView14);
        t.setText("Mickes bajs");
    }

    protected void getAll() {
        t.setText("ITS WORKING -- anikin always");
    }

    protected void getTemperature() {

    }

    protected void getPressure() {

    }

    protected void getHumidity() {

    }

    protected void getMagnet() {

    }

    protected void getGyroscope() {

    }

    protected void getAccelerometer() {

    }

    protected void clearAll() {

    }
}
