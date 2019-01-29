package com.example.cardiobook;

import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.google.gson.Gson;

/**
 *
 * @author zijunwu
 * This is a subclass of BaseDataOperation
 * Mainly focus on processing the data in listview
 *
 */

public class ListOperation extends BaseDataOperation {

    private ArrayAdapter<Measurements> adapter;

    public ListOperation(Context context) {
        super(context);
    }

    public void initList(ListView mainList) {
        LoadFromFile();
        adapter = new MeasurementsListAdapter(context, R.layout.list_item, myMeasurements.getM());
        mainList.setAdapter(adapter);
        mainList.setClickable(true);
        adapter.notifyDataSetChanged();

    }


    public void mListAdd(Intent intent) {

        Measurements item = getData(intent);
        if (item != null) {

            // From edit measurements
            if ( myMeasurements.isHold()) {
                myMeasurements.updateOldToNew(item);
                myMeasurements.clearHold();
            } else {
                myMeasurements.addM(item);
            }
            adapter.notifyDataSetChanged();
            SaveInFile();

            adapter.notifyDataSetChanged();

        } else {
            myMeasurements.clearHold();
        }


    }

    public Measurements getData(Intent intent) {

        Gson gson = new Gson();
        String objStr = intent.getStringExtra("newMeasurement");
        Measurements newMeasurement = gson.fromJson(objStr, Measurements.class);

//        when user go back
        intent.removeExtra("newMeasurement");

        return newMeasurement;

    }

    public void hold(Measurements m) {
        myMeasurements.hold(m);
        SaveInFile();
    }

    public void delete(Measurements m) {
        myMeasurements.deleteM(m);
        SaveInFile();
        adapter.notifyDataSetChanged();
    }
}
