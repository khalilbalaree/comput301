package com.example.cardiobook;

import java.util.ArrayList;

public class AllMyMeasurements {
    private ArrayList<Measurements> myMeasurements = new ArrayList<>();

    public ArrayList<Measurements> getM() {
        return myMeasurements;
    }


    public ArrayList<Measurements> addM(Measurements m) {
        myMeasurements.add(m);
        return myMeasurements;
    }

    public ArrayList<Measurements> deleteM(Measurements m) {
        myMeasurements.remove(m);
        return myMeasurements;
    }


}
