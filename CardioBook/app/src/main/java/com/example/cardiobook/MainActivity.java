package com.example.cardiobook;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;


/**
 * @author ZIJUN WU
 * @version 1.2
 * Copyright 2019, ZIJUN WU, https://github.com/khalilbalaree
 * <p>
 * This this the Main activity for CardioBook.
 * The app should allow the user to:
 * 1. show a list of measurements
 * 2. add a new measurement (which always appends to the bottom end of the list)
 * 3. view and edit the details of an existing measurement
 * 4. delete a measurement
 * 5. see unusual blood pressures highlighted or flagged
 */


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MyActivity";
    private ListOperation listOperation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView mainList = findViewById(R.id.MyList);

        listOperation = new ListOperation(MainActivity.this);
        listOperation.initList(mainList);

        final ListView finalMainList = mainList;
        mainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Measurements m = (Measurements) finalMainList.getItemAtPosition(position);
                Log.d(TAG, "onItemClick: " + m.getDate());

                setDialog(m);

            }
        });

        Button addButton = (Button) findViewById(R.id.AddButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InputInformationActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");

        listOperation.mListAdd(getIntent());

    }


    private void setDialog(final Measurements m) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Remove or Edit?");
        builder.setCancelable(true);

        builder.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MainActivity.this, InputInformationActivity.class);

                listOperation.hold(m);

                Gson gson = new Gson();
                String out = gson.toJson(m);
                intent.putExtra("oldMeasurement", out);
                startActivity(intent);

            }
        });

        builder.setNegativeButton("Remove", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                listOperation.delete(m);

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

}
