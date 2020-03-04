package com.example.goonlinejava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.loginButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView email = findViewById(R.id.email);
                TextView passwort = findViewById(R.id.passwort);

                if(email.getText().toString().equals("") || email.getText().toString().equals("E-Mail")) {
                    Toast.makeText(getApplicationContext(), "Ungueltige E-Mail", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(passwort.getText().toString().equals("") || passwort.getText().toString().equals("Passwort")) {
                        Toast.makeText(getApplicationContext(), "Ungueltiges Passwort", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        //TODO Serverrequest
                    }
                }
            }
        });

        findViewById(R.id.regiestrierenButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
