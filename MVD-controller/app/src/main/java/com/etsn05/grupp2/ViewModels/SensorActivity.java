package com.etsn05.grupp2.ViewModels;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SensorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        TextView t = (TextView)findViewById(R.id.textView14);
        t.setText("Mickes bajs");
    }
    protected void getAll(){

    }
}
