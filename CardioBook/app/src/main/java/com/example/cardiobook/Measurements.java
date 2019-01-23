package com.example.cardiobook;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Measurements {

    private Date date;
    private int systolic;
    private int diastolic;
    private int heartRate;
    private String comments;

    public Measurements(String sDate) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try{
            this.date = format.parse(sDate);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
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
}
