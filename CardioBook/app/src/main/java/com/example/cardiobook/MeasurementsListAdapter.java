package com.example.cardiobook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

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
            DateStrFormat dateStrFormat = new DateStrFormat();
            String sDate = dateStrFormat.DateToStr(getItem(position).getDate());
            String sSysbolic = Integer.toString(getItem(position).getSystolic());
            String sDiastolic = Integer.toString(getItem(position).getDiastolic());

            TextView date = (TextView) convertView.findViewById(R.id.textView1);
            TextView sysbolic = (TextView) convertView.findViewById(R.id.textView2);
            TextView diastolic = (TextView) convertView.findViewById(R.id.textView3);

            date.setText(sDate);
            sysbolic.setText(sSysbolic);
            diastolic.setText(sDiastolic);
        }

        return convertView;


    }
}
