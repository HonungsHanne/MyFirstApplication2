package com.etsn05.grupp2.ViewModels;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import Connection.ConnectionHandler;
import Model.SensorModel;

/**
 * Requirement 6.2.7 is implemented by default.
 */

/**
 * Activity that describes the functionality of the Sensor view.
 */

public class SensorActivity extends AppCompatActivity {
    private TextView StatusField;
    private SensorModel sensor;
    private ConnectionHandler ch;

    /**
     *
     * @param savedInstanceState - previous saved instance of the view, Is null if no previous view is saved.
     *
     * This method is called upon when the activity is started.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        ch = new ConnectionHandler();
        this.StatusField = (TextView) findViewById(R.id.textID);
        sensor = (SensorModel) getIntent().getSerializableExtra("DeviceModel");
        Switch sw = ((Switch)findViewById(R.id.switch1));
        /**
         * Sensor is set on/off depending on current status
         * requirement 6.2.2
         */
        if(sensor.status.equals("1")){
            sw.setChecked(true);
        }
        /**
         * When the on/off switch is klicked,
         * the server is updated accordingly
         * requirement 6.2.3
         */
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    ch.updateStatus(sensor,"1");
                } else {
                    ch.updateStatus(sensor,"0");
                }
            }
        });
        /**
         * Chosen device is displayed on this format
         * according to requirement 6.2.1
         */
        StatusField.setText(sensor.id + ", " + sensor.name + ", " + sensor.deviceAdress);


    }

    /**
     *
     * @param view
     * The View that calls on the method.
     *
     * This method uses calls updateTemperature from ConnectionHandler to update the Temperature value in the SensorModel.
     */

    /**
     * All gett buttons below are implemented
     * for requirement 6.2.4
     */
    public void onClickGetTemperature(View view) {
        TextView textTemperature = (TextView) findViewById(R.id.textTemperature);
        ch.updateTemp(sensor);
        textTemperature.setText(sensor.temperature);

    }

    /**
     *
     * @param view
     * The View that calls on the method.
     *
     * This method uses calls updatePressure from ConnectionHandler to update the Pressure value in the SensorModel.
     */
    public void onClickGetPressure(View view) {
        TextView textPressure = (TextView) findViewById(R.id.textPressure);
        ch.updatePressure(sensor);
        textPressure.setText(sensor.pressure);

    }

    /**
     *
     * @param view
     * The View that calls on the method.
     *
     * This method uses calls updateHumidity from ConnectionHandler to update the Humidity value in the SensorModel.
     */
    public void onClickGetHumidity(View view) {
        TextView textHumidity = (TextView) findViewById(R.id.textHumidity);
        ch.updateHumidity(sensor);
        textHumidity.setText(sensor.humidity);

    }

    /**
     *
     * @param view
     *
     * The View that calls on the method.
     *
     * This method uses calls updateMagnometer from ConnectionHandler to update the Magnometer value in the SensorModel.
     */

    public void onClickGetMagnetometer(View view) {
        TextView textMagnet = (TextView) findViewById(R.id.textMagnet);
        ch.updateMagnometer(sensor);
        textMagnet.setText(sensor.magnometer);

    }

    /**
     *
     * @param view
     *
     * The View that calls on the method.
     *
     * This method uses calls updateAccelerometer from ConnectionHandler to update the Accelerometer value in the SensorModel.
     */

    public void onClickGetGyroscope(View view) {
        TextView textGyrocope = (TextView) findViewById(R.id.textGyroscope);
        ch.updateGyroscope(sensor);
        textGyrocope.setText(sensor.gyroscope);

    }

    /**
     *
     * @param view
     * The View that calls on the method.
     *
     * This method uses calls updateAccelerometer from ConnectionHandler to update the Accelerometer value in the SensorModel.
     */

    public void onClickGetAccelerometer(View view) {
        TextView textAccelerometer = (TextView) findViewById(R.id.textAccelerometer);
        ch.updateAccelerometer(sensor);
        textAccelerometer.setText(sensor.accelorometer);

    }
    
    /**The View that calls on the method.
     *
     * @param view
     * The View that calls on the method.
     *
     * This Method clears all the fields that show sensor data.
     */
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

    /**
     *
     * @param view
     * The View that calls on the method.
     *
     * This method updates all of the fields in sensor device view.
     */
    public void onClickGetAll(View view) {
        TextView textTemperature = (TextView) findViewById(R.id.textTemperature);
        ch.updateTemp(sensor);
        textTemperature.setText(sensor.temperature);

        TextView textPressure = (TextView) findViewById(R.id.textPressure);
        ch.updatePressure(sensor);
        textPressure.setText(sensor.pressure);

        TextView textHumidity = (TextView) findViewById(R.id.textHumidity);
        ch.updateHumidity(sensor);
        textHumidity.setText(sensor.humidity);

        TextView textMagnet = (TextView) findViewById(R.id.textMagnet);
        ch.updateMagnometer(sensor);
        textMagnet.setText(sensor.magnometer);


        TextView textGyrocope = (TextView) findViewById(R.id.textGyroscope);
        ch.updateGyroscope(sensor);
        textGyrocope.setText(sensor.gyroscope);

        TextView textAccelerometer = (TextView) findViewById(R.id.textAccelerometer);
        ch.updateAccelerometer(sensor);
        textAccelerometer.setText(sensor.accelorometer);


    }


}
