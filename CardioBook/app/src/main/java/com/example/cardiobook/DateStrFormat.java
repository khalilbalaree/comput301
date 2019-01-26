package com.example.cardiobook;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ZIJUN WU
 * @version 1.1
 * Copyright 2019, ZIJUN WU, https://github.com/khalilbalaree
 * This is the class for tranferring the date to designated string form
 * or transfer the date string to date object.
 *
 */


public class DateStrFormat {

    private Date date = new Date();
    private String sDate;
    private DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public DateStrFormat(Date date) {
        this.date = date;
        this.sDate = this.format.format(date);
    }

    public DateStrFormat(String sDate) {
        this.sDate = sDate;

        try{
            this.date = this.format.parse(sDate);

        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

    }

    public String getsDate() {
        return this.sDate;
    }

    public Date getDate() {
        return this.date;
    }

    public String justSdate() {
        String[] parts = this.sDate.split(" ");
        return parts[0];

    }

    public String justStime() {
        String[] parts = this.sDate.split(" ");
        return parts[1];
    }
}
