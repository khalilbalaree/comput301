package com.example.cardiobook;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateStrFormat {

    private Date date = new Date();
    private DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public String DateToStr(Date date) {
        return this.format.format(date);

    }

    public Date StrToDate(String sDate) {

        try{
            this.date = this.format.parse(sDate);

        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        return this.date;

    }
}
