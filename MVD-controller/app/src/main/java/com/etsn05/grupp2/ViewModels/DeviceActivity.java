package com.etsn05.grupp2.ViewModels;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import Connection.ConnectionHandler;
import Model.DeviceModel;
import Model.LightbulbModel;
import Model.SensorModel;

/**
 * Activity that describes the functionality of the device view
 */

public class DeviceActivity extends AppCompatActivity {
    private ArrayList<DeviceModel> devices;
    private DeviceModel selected;
    private ConnectionHandler ch;
    private ArrayAdapter<DeviceModel> adapter;
    private AlphaAnimation buttonClick=new AlphaAnimation(1F,0.8F);


    /**
     * Creates the device view
     * @param savedInstanceState - previous saved instance of the view. Is null if no previous view is saved.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);
        Button b=(Button) findViewById(R.id.ButtonControlDevice);
        /**
         *controlbutton is disabled until listview is klicked
         *this until a list item is clicked. Requirement 6.1.5
         */
        b.setClickable(false);


        devices=new ArrayList<DeviceModel>();

        adapter=new ArrayAdapter<DeviceModel>(this,R.layout.list_device,devices);
        ListView listView=(ListView) findViewById(R.id.DeviceList);
        listView.setAdapter(adapter);
        listView.setTextFilterEnabled(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                selected=adapter.getItem(position);
                Button b=(Button) findViewById(R.id.ButtonControlDevice);
                b.setClickable(true);
            }
        });
    }


    /**
     * Called when the user clicks the Get Device button
     * Get a list of devices from ConnectionHandler
     * Builds list for view with available device
     * Implemented according to requirement 6.1.2
     */

    public void onClickGetDevices(View view) {http://www.dintaifung.com.my/
        ch=new ConnectionHandler();
        devices.clear();
        for(DeviceModel dm: ch.getDevices()){
            devices.add(dm);
        }
        adapter.notifyDataSetChanged();
        view.startAnimation(buttonClick);
    }

    /** Called when the user clicks Control Device
     * view is updated to either lightbulbview or
     * sensor view. This accordingly to requirement 6.1.6
     * and requirement 6.1.7
     */

    public void onClickControlDevice(View view) {
        view.startAnimation(buttonClick);
        if(selected instanceof SensorModel) {
            Intent intent = new Intent(this, SensorActivity.class);
            intent.putExtra("DeviceModel", (SensorModel)selected);
            startActivity(intent);
        } else if(selected instanceof LightbulbModel){
            Intent intent = new Intent(this, LightBulbActivity.class);
            intent.putExtra("DeviceModel", (LightbulbModel)selected);
            startActivity(intent);
        }

    }

    /*
     * When a device is selected in the list
     * Selected device is returned from the view
     */
    public void onSelected(ListView l, View v, int position, long id){
        DeviceModel selectedValue = adapter.getItem(position);
    }
}
