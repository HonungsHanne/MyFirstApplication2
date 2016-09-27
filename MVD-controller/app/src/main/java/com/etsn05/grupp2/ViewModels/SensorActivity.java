package com.etsn05.grupp2.ViewModels;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import Connection.ConnectionHandler;
import Model.SensorModel;


public class SensorActivity extends AppCompatActivity {
    TextView t;
    SensorModel sensor;
    ConnectionHandler ch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        ch = new ConnectionHandler();
        this.t = (TextView) findViewById(R.id.textID);
        t.setText("Mickes bajs");

    }

    public void onClickGetTemperature(View view) {
        TextView textTemperature = (TextView) findViewById(R.id.textTemperature);
        textTemperature.setText("very hot");
    }
    public void onClickGetPressure(View view){
        TextView textPressure = (TextView) findViewById(R.id.textPressure);
        textPressure.setText("Much pressure");
    }

    public void onClickGetHumidity(View view) {
        TextView textHumidity = (TextView) findViewById(R.id.textHumidity);
        textHumidity.setText("so moist");
    }

    public void onClickGetMagnetometer(View view) {
        TextView textMagnet = (TextView) findViewById(R.id.textMagnet);
        textMagnet.setText("much magnets wow");
    }

    public void onClickGetGyroscope(View view) {
        TextView textGyrscope = (TextView) findViewById(R.id.textGyroscope);
        textGyrscope.setText("im spinning! woooo");
    }

    public void onClickGetAccelerometer(View view) {
        TextView textAccelerometer = (TextView) findViewById(R.id.textAccelerometer);
        textAccelerometer.setText("OH GOD THE SPEED");
    }

    public void onClickClearAll(View view) {
        t.setText("Cleared Thank you");
    }


    public void onClickGetAll(View view) {
        t.setText("ITS WORKING -- anikin always");
    }

}
