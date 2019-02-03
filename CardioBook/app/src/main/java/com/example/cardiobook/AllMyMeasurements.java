package com.example.cardiobook;
import java.util.ArrayList;

/**
 * @author ZIJUN WU
 * @version 1.1
 * Copyright 2019, ZIJUN WU, https://github.com/khalilbalaree
 * Keep track of the measurements in this app
 */

public class AllMyMeasurements {
    private ArrayList<Measurements> myMeasurements = new ArrayList<>();
    private Measurements onHold;
    private Boolean isHold = false;
    private int index;

    /**
     * @return myMeasurement
     */
    public ArrayList<Measurements> getM() {
        return myMeasurements;
    }

    /**
     * add Measurements m to myMeasurements
     * @param m
     * @return myMeasurements
     */
    public ArrayList<Measurements> addM(Measurements m) {
        myMeasurements.add(m);
        return myMeasurements;
    }

    /**
     * delete Measurements m from myMeasurements
     * @param m
     * @return myMeasurements
     */
    public ArrayList<Measurements> deleteM(Measurements m) {
        myMeasurements.remove(m);
        return myMeasurements;
    }

    /**
     * a measurement in onhold if being view/edit
     * @param m
     */
    public void hold(Measurements m) {
        index = myMeasurements.indexOf(m);
        isHold = true;
        onHold = m;
    }

    /**
     * get what is on hold
     * @return onhold
     */
    public Measurements getHold() {
        return onHold;
    }

    /**
     * clear onhold item
     * is called when user finish editing or viewing
     */
    public void clearHold() {
        isHold = false;
        index = 0;
    }

    /**
     * check exits any onhold item
     * @return isHold
     */
    public boolean isHold() {
        return isHold;
    }

    /**
     * update the measurements in myMeasurements
     * called when user change something for a Measurement m
     * @param m
     */
    public void updateOldToNew(Measurements m) {
        myMeasurements.set(index, m);

    }

}
