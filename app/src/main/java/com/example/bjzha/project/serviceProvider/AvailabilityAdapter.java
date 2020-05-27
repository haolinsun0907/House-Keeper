package com.example.bjzha.project.serviceProvider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.bjzha.project.R;

import java.util.ArrayList;

public class AvailabilityAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<AvailabilityTime> availabilityTimes;

    public AvailabilityAdapter(Context context, ArrayList<AvailabilityTime> availabilityTimes){
        super(context, R.layout.service_provider_layout, availabilityTimes);
        this.context=context;
        this.availabilityTimes=availabilityTimes;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View rowView=inflater.inflate(R.layout.service_provider_layout, parent, false);
        TextView textView=(TextView)rowView.findViewById(R.id.textView);
        textView.setText(availabilityTimes.get(position).toString());
        return rowView;
    }
}
