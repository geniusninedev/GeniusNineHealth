package com.geniusnine.android.geniusninehealth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.geniusnine.android.geniusninehealth.DashBord.Appointments;
import com.geniusnine.android.geniusninehealth.DashBord.Drug;
import com.geniusnine.android.geniusninehealth.DashBord.Messages;
import com.geniusnine.android.geniusninehealth.DashBord.Patients;
import com.geniusnine.android.geniusninehealth.DashBord.Reminders;

public class MainActivity extends AppCompatActivity {

    ImageButton imageButtonappointment,imageButtonreminders,imageButtonpatient,imageButtonmessage,imageButtondrugs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageButtonappointment=(ImageButton)findViewById(R.id.imageButtonappointment);
        imageButtonreminders=(ImageButton)findViewById(R.id.imageButtonreminders);
        imageButtonpatient=(ImageButton)findViewById(R.id.imageButtonpatient);
        imageButtonmessage=(ImageButton)findViewById(R.id.imageButtonmessage);
        imageButtondrugs=(ImageButton)findViewById(R.id.imageButtondrugs);
        imageButtonappointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, Appointments.class);
                startActivity(intent);
            }
        });
        imageButtonreminders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, Reminders.class);
                startActivity(intent);
            }
        });
        imageButtonpatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Patients.class);
                startActivity(intent);
            }
        });
        imageButtonmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Messages.class);
                startActivity(intent);
            }
        });
        imageButtondrugs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Drug.class);
                startActivity(intent);
            }
        });
    }
}
