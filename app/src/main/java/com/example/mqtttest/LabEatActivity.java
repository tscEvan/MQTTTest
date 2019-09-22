package com.example.mqtttest;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Random;

public class LabEatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_eat);
        FloatingActionButton fab = findViewById(R.id.floating_bt_add_Store);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Coming soon..", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });

    }

    public void breakfast(View view){
        int i = new Random().nextInt(49);
        Snackbar.make(view, "Coming soon.."+i, Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
    }
    public void lunch(View view){
        Snackbar.make(view, "Coming soon..", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
    }
    public void dinner(View view){
        Snackbar.make(view, "Coming soon..", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
    }

}
