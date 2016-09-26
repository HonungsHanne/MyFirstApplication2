package com.etsn05.grupp2.ViewModels;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import Model.DeviceModel;

public class DeviceActivity extends AppCompatActivity {
    private ArrayList<DeviceModel> devices;
    private DeviceModel selected;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);
    }


    /** Called when the user clicks the Get Device button */
    public void onClickControlDevices(View view) {

    }

    /** Called when the user clicks Control Device */
    public void onClickControlDevice(View view) {
        Intent intent = new Intent(this, SensorActivity.class);
        // EditText editText = (EditText) findViewById(R.id.edit_message);
        // String message = editText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    /* when list is pushed*/

    public void onSelected(){

    }

    public void updateView(){

    }
}
