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
 *
 */

public class BaseDataOperation {

    private static final String FILENAME = "file.sav";
    protected Context context;
    protected static AllMyMeasurements myMeasurements = new AllMyMeasurements();

    public BaseDataOperation(Context context) {
        this.context = context;
    }

    public void LoadFromFile() {
        try {
            FileReader in = new FileReader(new File(context.getFilesDir(),FILENAME));
            Gson gson = new Gson();
            myMeasurements = gson.fromJson(in, AllMyMeasurements.class);

            if (myMeasurements == null) {
                myMeasurements = new AllMyMeasurements();
            }

            in.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void SaveInFile() {
        try {
            FileWriter out = new FileWriter(new File(context.getFilesDir(),FILENAME));
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
