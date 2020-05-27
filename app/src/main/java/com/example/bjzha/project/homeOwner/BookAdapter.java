package com.example.bjzha.project.homeOwner;

import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.bjzha.project.R;
import com.example.bjzha.project.serviceProvider.AvailabilityTime;

import java.util.ArrayList;
import java.util.Collections;

public class BookAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<AvailabilityTime> availabilityTimes;
    private ArrayList<String> index;

    public BookAdapter(Context context, ArrayList<AvailabilityTime> availabilityTimes){
        super(context, R.layout.service_layout,availabilityTimes);
        this.context=context;
        this.availabilityTimes=availabilityTimes;
        index=new ArrayList<String>();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        final LayoutInflater inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View rowView=inflater.inflate(R.layout.service_layout, parent, false);
        final TextView textView=(TextView)rowView.findViewById(R.id.infoText);
        final AvailabilityTime availabilityTime=availabilityTimes.get(position);
        textView.setText(availabilityTime.toString()+", is booked: "+availabilityTime.getBooked());
        final CheckBox checkBox=(CheckBox)rowView.findViewById(R.id.checkBox);
        checkBox.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked()){
                    index.add(Integer.toString(position));
                }
                else{
                    index.remove(Integer.toString(position));
                }
            }
        });
        return rowView;
    }

    public ArrayList<String> getIndex(){
        Collections.sort(index);
        return index;
    }
}
