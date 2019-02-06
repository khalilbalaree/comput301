package com.example.cardiobook;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
 * @author ZIJUN WU
 * @version 1.1
 * Copyright 2019, ZIJUN WU, https://github.com/khalilbalaree
 * Second activity for user to input the information.
 *
 * reference :
 * 1. https://github.com/Vikuku/lonelyTwitter/tree/t19lab2/app/src/main/java/ca/ualberta/cs/lonelytwitter
 * 2. https://stackoverflow.com/questions/5265913/how-to-use-putextra-and-getextra-for-string-data
 * 3. https://developer.android.com/guide/topics/ui/dialogs
 *
 */


public class InputInformationActivity extends AppCompatActivity {

    private static final String TAG = "MyActivity";

    private EditText dateText;
    private EditText timeText;
    private EditText systolicText;
    private EditText diastolicText;
    private EditText heartRateText;
    private EditText commentText;
    private Boolean dateCorrect;


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
                Intent intent = new Intent(InputInformationActivity.this, MainActivity.class);

                Gson gson = new Gson();
                String out = gson.toJson(getInfo());
                if (dateCorrect) {
                    intent.putExtra("newMeasurement", out);
                    startActivity(intent);
                } else {
                    setDialog();
                }

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

    /**
     * To get the information from the TextView
     * @return Measurement obj
     */
    public Measurements getInfo() {
        String DateAndTime;
        int sSystolic;
        int sDiastolic;
        int sHeartRate;


        String sDate = dateText.getText().toString();
        String sTime = timeText.getText().toString();
        DateAndTime = sDate + " " + sTime;

        DateStrFormat tempt = new DateStrFormat(DateAndTime);
        if (! tempt.isCorrectFormat()) {
            dateCorrect = false;
            DateAndTime = new DateStrFormat(new Date()).getsDate();
        } else {
            dateCorrect = true;
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

    /**
     * only used when edit the information
     * show the measurement m in TextView
     * @param m
     */
    public void setInfo(Measurements m) {

        DateStrFormat formatter = new DateStrFormat(m.getDate());

        dateText.setText(formatter.justSdate());
        timeText.setText(formatter.justStime());
        systolicText.setText(Integer.toString(m.getSystolic()));
        diastolicText.setText(Integer.toString(m.getDiastolic()));
        heartRateText.setText(Integer.toString(m.getHeartRate()));
        commentText.setText(m.getComments());

    }

    private void setDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Incorrect Date or Time type. Should be YYYY-MM-DD and hh:mm.");
        builder.setCancelable(true);

        builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
