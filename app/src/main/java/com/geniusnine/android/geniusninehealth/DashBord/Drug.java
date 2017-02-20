package com.geniusnine.android.geniusninehealth.DashBord;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;

import com.geniusnine.android.geniusninehealth.R;

/**
 * Created by Dev on 17-02-2017.
 */

public class Drug extends AppCompatActivity {

    ImageButton imageButtonappointment,imageButtonreminders,imageButtonpatient,imageButtonmessage,imageButtondrugs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug);
        imageButtonappointment=(ImageButton)findViewById(R.id.imageButtonappointment);
        imageButtonreminders=(ImageButton)findViewById(R.id.imageButtonreminders);
        imageButtonpatient=(ImageButton)findViewById(R.id.imageButtonpatient);
        imageButtonmessage=(ImageButton)findViewById(R.id.imageButtonmessage);
        imageButtondrugs=(ImageButton)findViewById(R.id.imageButtondrugs);
    }
}