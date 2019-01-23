package com.example.cardiobook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.gson.Gson;


public class InputInformation extends AppCompatActivity {

    private static final String TAG = "MyActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_information);

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

    public Measurements getInfo() {

        EditText dateText = findViewById(R.id.DateText);
        EditText timeText = findViewById(R.id.TimeText);
        EditText systolicText = findViewById(R.id.SystolicText);
        EditText diastolicText = findViewById(R.id.DiastolicText);
        EditText heartRateText = findViewById(R.id.HrText);
        EditText commentText = findViewById(R.id.CommentText);


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
}
