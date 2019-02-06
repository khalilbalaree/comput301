package com.example.cardiobook;

import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

/**
 * @author zijunwu
 * This is a subclass of BaseDataOperation
 * Mainly focus on processing the data in listview
 *
 * reference: https://github.com/Vikuku/lonelyTwitter/tree/t19lab2/app/src/main/java/ca/ualberta/cs/lonelytwitter
 */

public class ListOperation extends BaseDataOperation {

    private AllMyMeasurements myMeasurements;
    private ArrayAdapter<Measurements> adapter;

    /**
     * Constructor: Set the context for parent
     * @param context
     */
    public ListOperation(Context context) {
        super(context);
    }

    /**
     * Initialize the adapter
     * put the saved data to ListView
     * @param mainList
     */
    public void initList(ListView mainList) {
        myMeasurements = LoadFromFile();
        adapter = new MeasurementsListAdapter(context, R.layout.list_item, myMeasurements.getM());
        mainList.setAdapter(adapter);
        mainList.setClickable(true);
        adapter.notifyDataSetChanged();

    }

    /**
     * update myMeasurements
     * @param intent
     */
    public void mListAdd(Intent intent) {

        Measurements item = getData(intent);
        if (item != null) {

            // From edit measurements
            if (myMeasurements.isHold()) {
                myMeasurements.updateOldToNew(item);
                myMeasurements.clearHold();
            } else {
                myMeasurements.addM(item);
            }
            adapter.notifyDataSetChanged();
            SaveInFile(myMeasurements);

            adapter.notifyDataSetChanged();

        } else {
            myMeasurements.clearHold();
        }


    }

    /**
     * get the data from InputInformationActivity
     * @param intent
     * @return newMeasurement
     */
    public Measurements getData(Intent intent) {

        Gson gson = new Gson();
        String objStr = intent.getStringExtra("newMeasurement");
        Measurements newMeasurement = gson.fromJson(objStr, Measurements.class);

//        when user go back
        intent.removeExtra("newMeasurement");

        return newMeasurement;

    }

    /**
     * set onhold measurements in myMeasurements
     * @param m
     */
    public void hold(Measurements m) {
        myMeasurements.hold(m);
        SaveInFile(myMeasurements);
    }

    /**
     * delete measurement from myMeasurements
     * @param m
     */
    public void delete(Measurements m) {
        myMeasurements.deleteM(m);
        SaveInFile(myMeasurements);
        adapter.notifyDataSetChanged();
    }
}
