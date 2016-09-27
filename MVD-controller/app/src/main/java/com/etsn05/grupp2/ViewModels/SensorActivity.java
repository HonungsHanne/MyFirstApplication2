package com.etsn05.grupp2.ViewModels;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.Arrays;

import Connection.ConnectionHandler;
import Model.DeviceModel;


public class SensorActivity extends AppCompatActivity {
    TextView StatusField;
    DeviceModel sensor;
    ConnectionHandler ch;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        ch = new ConnectionHandler();
        this.StatusField = (TextView) findViewById(R.id.textID);
        sensor = (DeviceModel) getIntent().getSerializableExtra("DeviceModel");
        StatusField.setText(sensor.id + ", " + sensor.name + ", " + sensor.deviceAdress);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void onClickGetTemperature(View view) {
        TextView textTemperature = (TextView) findViewById(R.id.textTemperature);
        textTemperature.setText("very hot");
        //textPressure.setText(ch.doInBackground("PRESSURE"));
    }

    public void onClickGetPressure(View view) {
        TextView textPressure = (TextView) findViewById(R.id.textPressure);
        textPressure.setText("Much pressure");
        //textPressure.setText(ch.doInBackground("PRESSURE"));

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
        TextView textGyrocope = (TextView) findViewById(R.id.textGyroscope);
        textGyrocope.setText("im spinning! woooo");
    }

    public void onClickGetAccelerometer(View view) {
        TextView textAccelerometer = (TextView) findViewById(R.id.textAccelerometer);
        textAccelerometer.setText("OH GOD THE SPEED");
    }

    public void onClickClearAll(View view) {

        TextView text = (TextView) findViewById(R.id.textTemperature);
        text.setText("N/A");
        text = (TextView) findViewById(R.id.textHumidity);
        text.setText("N/A");
        text = (TextView) findViewById(R.id.textPressure);
        text.setText("N/A");
        text = (TextView) findViewById(R.id.textMagnet);
        text.setText("N/A");
        text = (TextView) findViewById(R.id.textGyroscope);
        text.setText("N/A");
        text = (TextView) findViewById(R.id.textAccelerometer);
        text.setText("N/A");
        text = (TextView) findViewById(R.id.textTemperature);
        text.setText("N/A");
    }


    public void onClickGetAll(View view) {
        StatusField.setText("ITS WORKING -- anikin always");
    }


}
