package com.example.bjzha.project.Administrator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.bjzha.project.R;

import java.util.ArrayList;
import java.util.Collections;

public class MyAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<Service> services;
    private ArrayList<String> index;

    public MyAdapter(Context context, ArrayList<Service> services){
        super(context, R.layout.service_layout, services);
        this.context=context;
        this.services=services;
        index=new ArrayList<String>();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        final LayoutInflater inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View rowView=inflater.inflate(R.layout.service_layout, parent, false);
        final TextView serviceInfo=(TextView)rowView.findViewById(R.id.infoText);
        final Service service=services.get(position);
        serviceInfo.setText(service.toString());
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
