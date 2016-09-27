package com.etsn05.grupp2.ViewModels;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import Connection.ConnectionHandler;
import Model.LightbulbModel;

public class LightBulbActivity extends AppCompatActivity {
    private LightbulbModel lightBulb;
    private ConnectionHandler ch;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_bulb);
        lightBulb = (LightbulbModel) getIntent().getSerializableExtra("DeviceModel");
        TextView title = (TextView) findViewById(R.id.titleLightBulb);
        title.setText(lightBulb.id + ", " + lightBulb.name + ", " + lightBulb.deviceAdress);
        ch = new ConnectionHandler();





    }



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
