package com.example.goonlinejava;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.List;

public class DisplayDevices extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_devices2);

        findViewById(R.id.addDevice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterDevice(DisplayDevices.this);
            }
        });

        Device d = new Device("Haus", "1.001", "Tufan", "Tafasolli");
        Device d2 = new Device("Haus2", "12.001", "Tufan2", "2Tafasolli");

        List<Device> deviceList = new ArrayList<>();
        deviceList.add(d);
        deviceList.add(d2);

        displayDevices(deviceList);
    }

    public void displayDevices(List<Device> deviceList) {
        ArrayList<String> deviceContentList = new ArrayList<>();

        for(Device device : deviceList) {
            deviceContentList.add(device.gebaeude + ", " + device.raum + ", " + device.nachnameBesitzer + ", " + device.vornameBesitzer);
        }

        updateSpinner(deviceContentList);
    }

    public void updateSpinner(List<String> deviceContent) {
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, deviceContent);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    public void enterDevice(Context c) {
        Context context = DisplayDevices.this;
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText roomBox = new EditText(context);
        roomBox.setHint("Room");
        layout.addView(roomBox);

        final EditText typeBox = new EditText(context);
        typeBox.setHint("Device Type");
        layout.addView(typeBox);

        AlertDialog dialog = new AlertDialog.Builder(c)
                .setTitle("Add a new device")
                .setMessage("Enter device")
                .setView(layout)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String room = String.valueOf(roomBox.getText());
                        final String type = String.valueOf(typeBox.getText());
                        new Thread(){
                            public void run(){
                                try {
                                    APIHandler.addDevice(room, type);
                                } catch(ProtocolException e) {
                                    e.printStackTrace();
                                }
                            }
                        }.start();
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();
    }
}
