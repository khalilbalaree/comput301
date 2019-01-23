package com.example.cardiobook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

//    private static final String FILENAME = "file.sav";
    private ListView mainList;
    private Measurements newMeasurement;
    private static ArrayList<Measurements> mList = new ArrayList<>();
    private ArrayAdapter<Measurements> adapter;
    private static AllMyMeasurements myMeasurements = new AllMyMeasurements();


    private static final String TAG = "MyActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainList = findViewById(R.id.MyList);
        adapter = new MeasurementsListAdapter(MainActivity.this, R.layout.list_item, mList);
        mainList.setAdapter(adapter);

        mList = myMeasurements.getM();
        adapter.notifyDataSetChanged();


    }


    @Override
    protected void onStart() {
        super.onStart();

        Button addButton = (Button) findViewById(R.id.AddButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InputInformation.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: " + "BackToActivity");
        Measurements item = getData();
        if (item != null) {
            mList = myMeasurements.addM(item);
            adapter.notifyDataSetChanged();
            Log.d(TAG, "onResume: " + "set");
        }



    }

    protected Measurements getData() {
        Intent intent = getIntent();

        Gson gson = new Gson();
        String objStr = intent.getStringExtra("newMeasurement");
        newMeasurement = gson.fromJson(objStr, Measurements.class);
        Log.d(TAG, "getData: " + objStr);

        return newMeasurement;

    }
}
