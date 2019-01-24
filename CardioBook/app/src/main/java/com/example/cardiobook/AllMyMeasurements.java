package com.example.cardiobook;

import android.util.Log;

import java.util.ArrayList;

/**
 *
 * @author ZIJUN WU
 * @version 1.1
 * Copyright 2019, ZIJUN WU, https://github.com/khalilbalaree
 *
 */

public class AllMyMeasurements {
    private ArrayList<Measurements> myMeasurements = new ArrayList<>();
    private Measurements onHold;
    private Boolean isHold = false;
    private int index;

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

    public void hold(Measurements m) {
        index = myMeasurements.indexOf(m);
        isHold = true;
        onHold = m;
    }

    public Measurements getHold() {
        return onHold;
    }

    public void clearHold() {
        isHold = false;
        index = 0;
    }

    public boolean isHold() {
        return isHold;
    }

    public void updateOldToNew(Measurements m) {
        myMeasurements.set(index, m);

    }

}
