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
 *
 * This this the Main activity for CardioBook.
 * The app should allow the user to:
 * 1. show a list of measurements
 * 2. add a new measurement (which always appends to the bottom end of the list)
 * 3. view and edit the details of an existing measurement
 * 4. delete a measurement
 * 5. see unusual blood pressures highlighted or flagged
 *
 * reference:
 * 1. https://developer.android.com/reference/android/app/Activity
 * 2. https://developer.android.com/guide/topics/ui/dialogs
 * 3. https://stackoverflow.com/questions/7145183/how-to-cancel-an-alertdialog
 */


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MyActivity";
    private ListOperation listOperation;


    /**
     * Initialize a ListView and a Add button in the MainActivity
     * @param savedInstanceState
     */
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

    /**
     * When user click the comfirm button in InputInformationActivity
     * send some data back to the MainActivity
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");

        listOperation.mListAdd(getIntent());

    }

    /**
     * @param m
     * set dialog when user click on the list item
     * user can remove or edit the item from this entry
     *
     */
    private void setDialog(final Measurements m) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("What you want to do?");
        builder.setCancelable(true);

        builder.setPositiveButton("View/Edit", new DialogInterface.OnClickListener() {
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

        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

}
