package com.example.cardiobook;

import android.content.Intent;
import android.content.RestrictionEntry;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static com.example.cardiobook.R.id.MyList;

public class MainActivity extends AppCompatActivity {

    private static final String FILENAME = "file.sav";
    private ListView mainList;
    private Measurements newMeasurement;
    private ArrayList<Measurements> mList = new ArrayList<Measurements>();
    private ArrayAdapter<Measurements> adapter;


    private static final String TAG = "MyActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        try{

//        mainList = (ListView) findViewById(R.id.MyList);
//        adapter = new ArrayAdapter<Measurements>(MainActivity.this, R.layout.list_item, mList);
//        mainList.setAdapter(adapter);
//
//        mList.add(newMeasurement);
//        adapter.notifyDataSetChanged();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            TextView textView = findViewById(R.id.textView);
            textView.setText(formatter.format(getData().getDate()));

        }catch (Exception e) {
            Log.d(TAG, "onCreate: " + "getIntentionFailed");
        }
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

    protected Measurements getData() {
        Intent intent = getIntent();

        Gson gson = new Gson();
        String objStr = intent.getStringExtra("newMeasurement");
        newMeasurement = gson.fromJson(objStr, Measurements.class);
        Log.d(TAG, "getData: " + objStr);

        return newMeasurement;

    }
}
