package com.example.cardiobook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.gson.Gson;

/**
 *
 * @author ZIJUN WU
 * @version 1.1
 * Copyright 2019, ZIJUN WU, https://github.com/khalilbalaree
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

        String sDate = dateText.getText().toString();
        String sTime = timeText.getText().toString();
        String sComment = commentText.getText().toString();
        int sSystolic = Integer.parseInt(systolicText.getText().toString());
        int sDiastolic = Integer.parseInt(diastolicText.getText().toString());
        int sHeartRate = Integer.parseInt(heartRateText.getText().toString());

        Measurements measurement = new Measurements(sDate + " " +sTime);
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
