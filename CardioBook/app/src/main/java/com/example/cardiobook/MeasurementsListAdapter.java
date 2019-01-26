package com.example.cardiobook;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

/**
 *
 * @author ZIJUN WU
 * @version 1.1
 * Copyright 2019, ZIJUN WU, https://github.com/khalilbalaree
 *
 */

public class MeasurementsListAdapter extends ArrayAdapter<Measurements> {

    private Context mContext;
    private int mResource;

    public MeasurementsListAdapter(Context context, int resource, ArrayList<Measurements> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
//        return super.getView(position, convertView, parent);


        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        Measurements item = getItem(position);

        if (item != null) {

            if (getItem(position).isFlag()) {
                convertView.setBackgroundColor(Color.parseColor("#FFFFE0")); // Hightlight in light yellow
            }

            TextView textView = (TextView) convertView.findViewById(R.id.textView1);
            textView.setTextColor(Color.BLACK);

            DateStrFormat dateStrFormat = new DateStrFormat(getItem(position).getDate());
            String sDate = dateStrFormat.getsDate();
            String sSysbolic = Integer.toString(getItem(position).getSystolic());
            String sDiastolic = Integer.toString(getItem(position).getDiastolic());
            String sHeartRate = Integer.toString(getItem(position).getHeartRate());

            TextView date = (TextView) convertView.findViewById(R.id.textView1);
            TextView sysbolic = (TextView) convertView.findViewById(R.id.textView2);
            TextView diastolic = (TextView) convertView.findViewById(R.id.textView3);
            TextView heartRate = convertView.findViewById(R.id.textView4);

            date.setText(sDate);
            sysbolic.setText("Sysbolic: " + sSysbolic);
            diastolic.setText("Diastolic: " + sDiastolic);
            heartRate.setText("HeartRate: " + sHeartRate);
        }

        return convertView;


    }
}
