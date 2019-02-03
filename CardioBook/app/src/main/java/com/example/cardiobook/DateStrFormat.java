package com.example.cardiobook;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ZIJUN WU
 * @version 1.1
 * Copyright 2019, ZIJUN WU, https://github.com/khalilbalaree
 * This is the class for tranferring the date to designated string form
 * or transfer the date string to date object.
 */


public class DateStrFormat {

    private Date date = new Date();
    private String sDate;
    private DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private boolean IsCorrectFormat;

    /**
     * Constructor1, get a Date type and set local date and sDate
     * @param date
     */
    public DateStrFormat(Date date) {
        this.date = date;
        this.sDate = this.format.format(date);
    }

    /**
     * Constructor2, get a String type and set local date and sDate
     * @param sDate
     */
    public DateStrFormat(String sDate) {
        this.sDate = sDate;

        try {
            this.date = this.format.parse(sDate);
            IsCorrectFormat = true;

        } catch (java.text.ParseException e) {
            e.printStackTrace();
            IsCorrectFormat = false;
        }

    }

    public String getsDate() {
        return this.sDate;
    }

    public Date getDate() {
        return this.date;
    }

    /**
     * @return a string just include date
     */
    public String justSdate() {
        String[] parts = this.sDate.split(" ");
        return parts[0];

    }

    /**
     * @return a string just include time
     */
    public String justStime() {
        String[] parts = this.sDate.split(" ");
        return parts[1];
    }

    /**
     * check if the incoming parameter is in correct format
     * @return Boolean IsCorrectFormat
     */
    public boolean isCorrectFormat() {
        return IsCorrectFormat;
    }
}
