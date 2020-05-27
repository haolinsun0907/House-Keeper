package com.example.bjzha.project.homeOwner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bjzha.project.Account;
import com.example.bjzha.project.Administrator.Service;
import com.example.bjzha.project.MyDataBase;
import com.example.bjzha.project.R;
import com.example.bjzha.project.serviceProvider.Availability;
import com.example.bjzha.project.serviceProvider.AvailabilityTime;

import java.util.ArrayList;
import java.util.HashMap;

public class BookList extends AppCompatActivity {
    private ListView listView;
    private RateAdapter rateAdapter;
    private ArrayList<HashMap<String, Object>> informations;
    private MyDataBase myDataBase;
    private ArrayList<Service> services;
    private ArrayList<AvailabilityTime> availabilityTimes;
    private TextView textView;
    private ArrayList<Account> accounts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        listView=(ListView)findViewById(R.id.listView);
        myDataBase=new MyDataBase(this);
        informations=myDataBase.findHO();
        textView=(TextView)findViewById(R.id.textView);
        services=new ArrayList<Service>();
        availabilityTimes=new ArrayList<AvailabilityTime>();
        accounts=new ArrayList<Account>();
        for (int i=0;i<informations.size();i++){
            HashMap<String, Object> map=informations.get(i);
            services.add((Service)map.get("service"));
            availabilityTimes.add((AvailabilityTime)map.get("time"));
            accounts.add((Account)map.get("spaccount"));
        }
        rateAdapter =new RateAdapter(this, services, availabilityTimes);
        listView.setAdapter(rateAdapter);
    }

    public void continueBtn(View view){
        boolean error=false;
        ArrayList<String> index=rateAdapter.getIndex();
        if(index.size()!=1){
            textView.setText("Please only choose one to book!");
        }
        else{
            Intent intent=new Intent(this, Rating.class);
            intent.putExtra("spaccount", accounts.get(Integer.parseInt(index.get(0))));
            intent.putExtra("service", services.get(Integer.parseInt(index.get(0))));
            intent.putExtra("time", availabilityTimes.get(Integer.parseInt(index.get(0))));
            startActivity(intent);
            finish();
        }
    }
}
