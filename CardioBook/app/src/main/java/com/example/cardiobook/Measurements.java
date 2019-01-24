package com.example.cardiobook;

import java.util.Date;

public class Measurements {

    private Date date;
    private int systolic;
    private int diastolic;
    private int heartRate;
    private String comments;
    private boolean flag;

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



        return flag;
    }

}
