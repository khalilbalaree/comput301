package com.example.cardiobook;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

        //Set list view adapter
        mainList = findViewById(R.id.MyList);
        adapter = new MeasurementsListAdapter(MainActivity.this, R.layout.list_item, mList);
        mainList.setAdapter(adapter);
        mainList.setClickable(true);

        //Connect myMeasurements this mList
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

        mainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Measurements m = (Measurements)mainList.getItemAtPosition(position);
                Log.d(TAG, "onItemClick: " + m.getDate());

                setDialog(m);

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: " + "BackToActivity");

        mListAdd();


    }

    protected void mListAdd() {

        Measurements item = getData();
        if (item != null) {

            // From edit measurements
            if (! myMeasurements.is_clear_hold()) {
                mList.remove(myMeasurements.getHold());
                myMeasurements.clearHold();
            }



            mList = myMeasurements.addM(item);
            adapter.notifyDataSetChanged();
            Log.d(TAG, "onResume: " + mList.size());
        } else {
            myMeasurements.clearHold();
        }


    }

    protected Measurements getData() {
        Intent intent = getIntent();

        Gson gson = new Gson();
        String objStr = intent.getStringExtra("newMeasurement");
        newMeasurement = gson.fromJson(objStr, Measurements.class);

//        when user go back
        intent.removeExtra("newMeasurement");
        Log.d(TAG, "getData: " + objStr);

        return newMeasurement;

    }



    protected void setDialog(final Measurements m) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Remove or Edit?");
        builder.setCancelable(true);

        builder.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MainActivity.this, InputInformation.class);

                myMeasurements.hold(m);

                Gson gson = new Gson();
                String out = gson.toJson(m);
                intent.putExtra("oldMeasurement", out);
                startActivity(intent);

            }
        });

        builder.setNegativeButton("Remove", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mList = myMeasurements.deleteM(m);
                adapter.notifyDataSetChanged();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}
