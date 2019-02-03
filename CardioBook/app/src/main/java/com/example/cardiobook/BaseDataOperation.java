package com.example.cardiobook;

import android.content.Context;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author zijunwu
 * This class is for process the data in this app
 * BaseDataOperation mainly focus on file data process
 */

public class BaseDataOperation {

    protected Context context;
    private static final String FILENAME = "file.sav";
    private AllMyMeasurements myMeasurements;

    /**
     * Constructor: get the context from MainActivity
     * @param context
     */
    public BaseDataOperation(Context context) {
        this.context = context;
    }

    /**
     * Load data from file
     * @return myMeasurements:AllMyMeasurements
     */

    public AllMyMeasurements LoadFromFile() {
        try {
            FileReader in = new FileReader(new File(context.getFilesDir(), FILENAME));
            Gson gson = new Gson();
            myMeasurements = gson.fromJson(in, AllMyMeasurements.class);

            if (myMeasurements == null) {
                myMeasurements = new AllMyMeasurements();
            }

            in.close();

        } catch (Exception e) {
            e.printStackTrace();
            myMeasurements = new AllMyMeasurements();
        }

        return myMeasurements;
    }

    /**
     * Save data in file
     * @param myMeasurements
     */
    public void SaveInFile(AllMyMeasurements myMeasurements) {
        try {
            FileWriter out = new FileWriter(new File(context.getFilesDir(), FILENAME));
            Gson gson = new Gson();
            gson.toJson(myMeasurements, out);

            out.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
