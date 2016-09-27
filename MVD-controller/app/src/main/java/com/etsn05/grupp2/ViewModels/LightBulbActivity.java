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




    }



    public void onClickGet(View view) {
        lightBulb.blue="TO BE PARSED FROM JSON";
        lightBulb.red="TO BE PARSED FROM JSON";
        lightBulb.green="TO BE PARSED FROM JSON";
        lightBulb.white="TO BE PARSED FROM JSON";
        lightBulb.color="#"+lightBulb.red+lightBulb.green+lightBulb.blue+lightBulb.white;
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
        lightBulb.red=textRed.getText().toString();
        lightBulb.green=textGreen.getText().toString();
        lightBulb.blue=textBlue.getText().toString();
        lightBulb.white=textWhite.getText().toString();
        lightBulb.color="#"+lightBulb.red+lightBulb.green+lightBulb.blue+lightBulb.white;


        // SEND COLORS

    }
}
