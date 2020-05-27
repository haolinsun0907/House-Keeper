package com.example.bjzha.project.serviceProvider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.bjzha.project.Administrator.Service;
import com.example.bjzha.project.R;

import java.util.ArrayList;

public class ServiceAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<Service> services;

    public ServiceAdapter(Context context, ArrayList<Service> services){
        super(context, R.layout.service_provider_layout,services);
        this.context=context;
        this.services=services;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        final LayoutInflater inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View rowView=inflater.inflate(R.layout.service_provider_layout, parent, false);
        TextView textView=(TextView)rowView.findViewById(R.id.textView);
        textView.setText(services.get(position).toString());
        return rowView;
    }
}
