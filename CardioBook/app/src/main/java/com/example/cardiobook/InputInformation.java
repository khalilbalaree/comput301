package com.example.cardiobook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.gson.Gson;

import java.util.Date;

/**
 *
 * @author ZIJUN WU
 * @version 1.1
 * Copyright 2019, ZIJUN WU, https://github.com/khalilbalaree
 * Second activity for user to input the information.
 *
 */


public class InputInformation extends AppCompatActivity {

    private static final String TAG = "MyActivity";

    private EditText dateText;
    private EditText timeText;
    private EditText systolicText;
    private EditText diastolicText;
    private EditText heartRateText;
    private EditText commentText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_information);

        dateText = findViewById(R.id.DateText);
        timeText = findViewById(R.id.TimeText);
        systolicText = findViewById(R.id.SystolicText);
        diastolicText = findViewById(R.id.DiastolicText);
        heartRateText = findViewById(R.id.HrText);
        commentText = findViewById(R.id.CommentText);

    }

    @Override
    protected void onStart() {
        super.onStart();

        Button comfirmButton = (Button) findViewById(R.id.ComfirmButton);

        comfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InputInformation.this, MainActivity.class);

                Gson gson = new Gson();
                String out = gson.toJson(getInfo());
                intent.putExtra("newMeasurement", out);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();


        Gson gson = new Gson();
        String objStr = intent.getStringExtra("oldMeasurement");
        Log.d(TAG, "onResume: " + objStr);
        if (objStr != null) {

            Measurements oldMeasurement = gson.fromJson(objStr, Measurements.class);
            setInfo(oldMeasurement);

        }


    }

    public Measurements getInfo() {
        String DateAndTime;
        int sSystolic;
        int sDiastolic;
        int sHeartRate;

        try {
            String sDate = dateText.getText().toString();
            String sTime = timeText.getText().toString();
            DateAndTime = sDate + " " + sTime;
        } catch (Exception e) {
            DateAndTime = new DateStrFormat(new Date()).getsDate();
        }
        try {
            sSystolic = Integer.parseInt(systolicText.getText().toString());
        } catch (Exception e) {
            sSystolic = 0;
        }
        try {
            sDiastolic = Integer.parseInt(diastolicText.getText().toString());
        } catch (Exception e) {
            sDiastolic = 0;
        }
        try {
            sHeartRate = Integer.parseInt(heartRateText.getText().toString());
        } catch (Exception e) {
            sHeartRate = 0;
        }

        String sComment = commentText.getText().toString();

        Measurements measurement = new Measurements(DateAndTime);
        measurement.setSystolic(sSystolic);
        measurement.setDiastolic(sDiastolic);
        measurement.setHeartRate(sHeartRate);
        measurement.setComments(sComment);

        return measurement;


    }

    public void setInfo(Measurements m) {

        DateStrFormat formatter = new DateStrFormat(m.getDate());

        dateText.setText(formatter.justSdate());
        timeText.setText(formatter.justStime());
        systolicText.setText(Integer.toString(m.getSystolic()));
        diastolicText.setText(Integer.toString(m.getDiastolic()));
        heartRateText.setText(Integer.toString(m.getHeartRate()));
        commentText.setText(m.getComments());

    }

}
