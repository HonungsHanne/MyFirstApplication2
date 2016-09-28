package com.etsn05.grupp2.ViewModels;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import Connection.ConnectionHandler;
import Model.SensorModel;


public class SensorActivity extends AppCompatActivity {
    TextView StatusField;
    SensorModel sensor;
    ConnectionHandler ch;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        ch = new ConnectionHandler();
        this.StatusField = (TextView) findViewById(R.id.textID);
        sensor = (SensorModel) getIntent().getSerializableExtra("DeviceModel");
        Switch sw = ((Switch)findViewById(R.id.switch1));
        if(sensor.status.equals("1")){
            sw.setChecked(true);
        }

        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    ch.updateStatus(sensor,"1");
                } else {
                    ch.updateStatus(sensor,"0");
                }
            }
        });
        StatusField.setText(sensor.id + ", " + sensor.name + ", " + sensor.deviceAdress);


    }

    public void onClickGetStatus(View view){

    }

    public void onClickGetTemperature(View view) {
        TextView textTemperature = (TextView) findViewById(R.id.textTemperature);
        ch.updateTemp(sensor);
        textTemperature.setText(sensor.temperature);

    }

    public void onClickGetPressure(View view) {
        TextView textPressure = (TextView) findViewById(R.id.textPressure);
        //textPressure.setText(ch.doInBackground("PRESSURE"));
        ch.updatePressure(sensor);
        textPressure.setText(sensor.pressure);

    }

    public void onClickGetHumidity(View view) {
        TextView textHumidity = (TextView) findViewById(R.id.textHumidity);
        ch.updateHumidity(sensor);
        textHumidity.setText(sensor.humidity);

    }

    public void onClickGetMagnetometer(View view) {
        TextView textMagnet = (TextView) findViewById(R.id.textMagnet);
        ch.updateMagnometer(sensor);
        textMagnet.setText(sensor.magnometer);

    }

    public void onClickGetGyroscope(View view) {
        TextView textGyrocope = (TextView) findViewById(R.id.textGyroscope);
        ch.updateGyroscope(sensor);
        textGyrocope.setText(sensor.gyroscope);

    }

    public void onClickGetAccelerometer(View view) {
        TextView textAccelerometer = (TextView) findViewById(R.id.textAccelerometer);
        ch.updateAccelerometer(sensor);
        textAccelerometer.setText(sensor.accelorometer);

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
