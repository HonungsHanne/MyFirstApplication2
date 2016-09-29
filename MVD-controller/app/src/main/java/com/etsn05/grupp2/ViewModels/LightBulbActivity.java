package com.etsn05.grupp2.ViewModels;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import Connection.ConnectionHandler;
import Model.LightbulbModel;

/**
 * requirement 6.3.6 is implemented by default.
 */
public class LightBulbActivity extends AppCompatActivity {
    private LightbulbModel lightBulb;
    private ConnectionHandler ch;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_bulb);

        lightBulb = (LightbulbModel) getIntent().getSerializableExtra("DeviceModel");
        TextView title = (TextView) findViewById(R.id.titleLightBulb);

        /**
         * Device info is printed according to requirement 6.3.1
         */
        title.setText(lightBulb.id + ", " + lightBulb.name + ", " + lightBulb.deviceAdress);
        Switch sw = ((Switch)findViewById(R.id.switch1));
        /**
         * On/off switch is set to current status
         * of the device in the server. Requirement 6.3.2
         */
        if(lightBulb.status.equals("1")){
            sw.setChecked(true);
        }

        /**
         * On/off switch changes the status on the server
         * requirement 6.3.3
         */
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    ch.updateStatus(lightBulb,"1");
                    //måste kolla om lightbulb fungerar
                    lightBulb.status="1";
                } else {
                    ch.updateStatus(lightBulb,"0");
                    //måste kolla om update fungerar
                    lightBulb.status="0";
                }
            }
        });
        ch = new ConnectionHandler();
    }


    /**
     * fetches current color values of the device on the server
     * requirement 6.3.4
     */
    public void onClickGet(View view) {
        if(lightBulb != null) {
            ch.updateColor(lightBulb);
        }
        String color = lightBulb.color;
        System.out.println(lightBulb.color + "This is the string");
        String [] RGB = new String [4];
        RGB[0] = color.substring(0,2); //Hämtar röd
        RGB[1] = color.substring(2,4); //hämtar grön
        RGB[2] = color.substring(4,6); //hämtar blå
        RGB[3] = color.substring(6); //hämtar vit
        lightBulb.red = RGB[0];
        lightBulb.green = RGB[1];
        lightBulb.blue = RGB[2];
        lightBulb.white = RGB[3];

        TextView textRed = (TextView) findViewById(R.id.textRed);
        TextView textGreen = (TextView) findViewById(R.id.textGreen);
        TextView textBlue = (TextView) findViewById(R.id.textBlue);
        TextView textWhite = (TextView) findViewById(R.id.textWhite);
        textRed.setText(lightBulb.red);
        textGreen.setText(lightBulb.green);
        textBlue.setText(lightBulb.blue);
        textWhite.setText(lightBulb.white);


    }

    /**
     * Sets the color values on the servers
     * based on the current values on the view
     * requirement 6.3.5
     */
    public void onClickSet(View view) {
        TextView textRed = (TextView) findViewById(R.id.textRed);
        TextView textGreen = (TextView) findViewById(R.id.textGreen);
        TextView textBlue = (TextView) findViewById(R.id.textBlue);
        TextView textWhite = (TextView) findViewById(R.id.textWhite);
        lightBulb.red = textRed.getText().toString();
        lightBulb.green = textGreen.getText().toString();
        lightBulb.blue = textBlue.getText().toString();
        lightBulb.white = textWhite.getText().toString();
        lightBulb.color =lightBulb.red+lightBulb.green+lightBulb.blue+lightBulb.white;
        ch.setColor(lightBulb);

        // SEND COLORS

    }
}
