package com.example.goonlinejava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class DisplayDevices extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_devices2);

        /*Device d = new Device("Haus", "1.001", "Tufan", "Tafasolli");

        List<Device> deviceList = new ArrayList<>();
        deviceList.add(d);
        ArrayAdapter<CharSequence> aa = ArrayAdapter.createFromResource(this, R.a, android.R.layout.simple_spinner_item);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = (Spinner) findViewById(R.id.spinner);
        sItems.setAdapter(aa);*/
    }
}
