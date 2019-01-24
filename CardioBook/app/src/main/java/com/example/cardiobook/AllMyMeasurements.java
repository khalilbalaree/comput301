package com.example.cardiobook;

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
    private Boolean isClearHold = true;

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
        isClearHold = false;
        onHold = m;
    }

    public Measurements getHold() {
        return onHold;
    }

    public void clearHold() {
        isClearHold = true;
    }

    public boolean is_clear_hold() {
        return isClearHold;
    }

}
