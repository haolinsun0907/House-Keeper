package com.example.bjzha.project.serviceProvider;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bjzha.project.R;

import java.util.ArrayList;

public class AvailabilityShow extends AppCompatActivity {
    private ArrayList<AvailabilityTime> availabilityTimes;
    private ListView listView;
    private AvailabilityAdapter availabilityAdapter;
    private static final int REQUEST_MODIFY=100;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_availability_show);
        availabilityTimes=ServiceProvider.getAvailability();
        listView=findViewById(R.id.list);
        refresh();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AvailabilityTime selectedItem=availabilityTimes.get(position);
                int index=availabilityTimes.indexOf(selectedItem);
                Intent intent=new Intent(getApplicationContext(), Availability.class);
                String date;
                int startHour, startMin, endHour, endMin;
                startHour=selectedItem.getStartHour();
                startMin=selectedItem.getStartMin();
                endHour=selectedItem.getEndHour();
                endMin=selectedItem.getEndMin();
                intent.putExtra("Start hour", Integer.toString(startHour));
                intent.putExtra("Start min", Integer.toString(startMin));
                intent.putExtra("End hour", Integer.toString(endHour));
                intent.putExtra("End min", Integer.toString(endMin));
                intent.putExtra("Position", Integer.toString(index));
                startActivityForResult(intent, REQUEST_MODIFY);
            }
        });
    }

    public void refresh(){
        availabilityAdapter=new AvailabilityAdapter(this, availabilityTimes);
        listView.setAdapter(availabilityAdapter);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        refresh();
    }
}
