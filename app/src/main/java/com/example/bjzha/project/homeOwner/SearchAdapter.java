package com.example.bjzha.project.homeOwner;

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
import java.util.HashMap;

public class SearchAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<HashMap<String,String>> information;
    private ArrayList<String> index;

    public SearchAdapter(Context context, ArrayList<HashMap<String,String>> information){
        super(context, R.layout.service_layout, information);
        this.context=context;
        this.information=information;
        index=new ArrayList<String>();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        final LayoutInflater inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View rowView=inflater.inflate(R.layout.service_layout, parent, false);
        final TextView textView=(TextView)rowView.findViewById(R.id.infoText);
        final HashMap<String,String> map=information.get(position);
        String firstName,lastName,serviceName,serviceRate,rating;
        firstName=map.get("first name");
        lastName=map.get("last name");
        serviceName=map.get("service name");
        serviceRate=map.get("service rate");
        rating=map.get("rating");
        textView.setText(firstName+" "+lastName+" provides "+serviceName+" with "+serviceRate+"$ hourly rate,"+" rating: "+rating);
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
