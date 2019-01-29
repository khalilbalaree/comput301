package com.example.cardiobook;

import java.util.Date;

/**
 * @author ZIJUN WU
 * @version 1.1
 * Copyright 2019, ZIJUN WU, https://github.com/khalilbalaree
 */

public class Measurements {

    private Date date;
    private int systolic;
    private int diastolic;
    private int heartRate;
    private String comments;

    public Measurements(String sDate) {
        DateStrFormat format = new DateStrFormat(sDate);
        this.date = format.getDate();
    }

    public Date getDate() {
        return date;
    }

    public int getSystolic() {
        return systolic;
    }

    public void setSystolic(int systolic) {
        this.systolic = systolic;
    }

    public int getDiastolic() {
        return diastolic;
    }

    public void setDiastolic(int diastolic) {
        this.diastolic = diastolic;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public boolean isFlag() {
        if (systolic < 90 || systolic > 140 || diastolic < 60 || diastolic > 90) {
            return true;
        } else {
            return false;
        }
    }

    public boolean SystolicIsFlag() {
        if (systolic < 90 || systolic > 140) {
            return true;
        } else {
            return false;
        }
    }

    public boolean DiastolicIsFlag() {
        if (diastolic < 60 || diastolic > 90) {
            return true;
        } else {
            return false;
        }
    }


}
