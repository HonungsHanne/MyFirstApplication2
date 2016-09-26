package com.etsn05.grupp2.ViewModels;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import Connection.ConnectionHandler;
import Model.DeviceModel;

public class DeviceActivity extends AppCompatActivity {
    private ArrayList<DeviceModel> devices;
    private DeviceModel selected;
    private ConnectionHandler ch;
    private ArrayAdapter<DeviceModel> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);

        Button b=(Button) findViewById(R.id.ButtonControlDevice);
        b.setClickable(false);

        devices=new ArrayList<DeviceModel>();
        devices.add(new DeviceModel("1","name1","ip1","on"));
        devices.add(new DeviceModel("2","name2","ip2","off"));
        devices.add(new DeviceModel("3","name3","ip3","on"));
        devices.add(new DeviceModel("4","name4","ip4","off"));
        adapter=new ArrayAdapter<DeviceModel>(this,R.layout.list_device,devices);
        ListView listView=(ListView) findViewById(R.id.DeviceList);
        listView.setAdapter(adapter);
        listView.setTextFilterEnabled(true);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setSelector(android.R.color.darker_gray);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, show a toast with the TextView text

                System.out.println(parent.getAdapter().getItem(position));
                Button b=(Button) findViewById(R.id.ButtonControlDevice);
                b.setClickable(true);
            }
        });

    }


    /** Called when the user clicks the Get Device button */
    public void onClickGetDevices(View view) {
        ch=new ConnectionHandler();
        System.out.println(ch.execute());

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
    public void onSelected(ListView l, View v, int position, long id){
        DeviceModel selectedValue = adapter.getItem(position);
        System.out.println(selectedValue);
    }

    public void updateView(){

    }
}
