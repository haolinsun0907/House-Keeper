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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class Book extends AppCompatActivity {
    private boolean serviceType,timeType,rateType;
    private MyDataBase myDataBase;
    private ListView listView;
    private SearchAdapter searchAdapter;
    private TextView textView;
    private HashMap<String, ArrayList<AvailabilityTime>> times;
    private HashMap<String, Account> accountMap;
    private HashMap<String, Service> serviceMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        textView=(TextView)findViewById(R.id.textView);
        Intent intent=getIntent();
        myDataBase=new MyDataBase(this);
        listView=(ListView)findViewById(R.id.listView);
        serviceType=Boolean.parseBoolean(intent.getStringExtra("service type"));
        timeType=Boolean.parseBoolean(intent.getStringExtra("time type"));
        rateType=Boolean.parseBoolean(intent.getStringExtra("rate type"));
        textView.setText(Boolean.toString(timeType));
        times=new HashMap<String, ArrayList<AvailabilityTime>>();
        accountMap=new HashMap<String, Account>();
        serviceMap=new HashMap<String, Service>();
        if(serviceType){
            String serviceName=intent.getStringExtra("service name");
            ArrayList<HashMap<String, Object>> maps=myDataBase.searchByService(serviceName);
            ArrayList<HashMap<String,String>> information=new ArrayList<HashMap<String,String>>();
            for(int i=0;i<maps.size();i++){
                HashMap<String, Object> map=maps.get(i);
                HashMap<String, String> map2=new HashMap<String, String>();
                map2.put("first name",(String)map.get("first name"));
                map2.put("last name",(String)map.get("last name"));
                map2.put("service name",(String)map.get("service name"));
                map2.put("service rate",(String)map.get("service rate"));
                map2.put("rating",(String)map.get("rating"));
                information.add(map2);
                times.put(Integer.toString(i), (ArrayList<AvailabilityTime>)map.get("times"));
                accountMap.put(Integer.toString(i), new Account((String)map.get("first name"), (String)map.get("last name")));
                serviceMap.put(Integer.toString(i), new Service((String)map.get("service name"), Integer.parseInt((String)map.get("service rate"))));
            }
            searchAdapter=new SearchAdapter(this, information);
            listView.setAdapter(searchAdapter);
        }
        if(timeType){
            AvailabilityTime availabilityTime=(AvailabilityTime)intent.getSerializableExtra("time");
            ArrayList<HashMap<String, Object>> maps=myDataBase.searchByTime(availabilityTime);
            ArrayList<HashMap<String,String>> information=new ArrayList<HashMap<String,String>>();
            for(int i=0;i<maps.size();i++){
                HashMap<String, Object> map=maps.get(i);
                ArrayList<Service> services=(ArrayList<Service>)map.get("services");
                for(int j=0;j<services.size();j++){
                    HashMap<String, String> map2=new HashMap<String, String>();
                    map2.put("first name",(String)map.get("first name"));
                    map2.put("last name",(String)map.get("last name"));
                    map2.put("service name",services.get(j).getName());
                    map2.put("service rate",Integer.toString(services.get(j).getRate()));
                    map2.put("rating",Integer.toString(services.get(j).getRating()));
                    information.add(map2);
                    times.put(Integer.toString(j), (ArrayList<AvailabilityTime>)map.get("times"));
                    accountMap.put(Integer.toString(j), new Account((String)map.get("first name"), (String)map.get("last name")));
                    serviceMap.put(Integer.toString(j), services.get(j));
                }
            }
            searchAdapter=new SearchAdapter(this, information);
            listView.setAdapter(searchAdapter);
        }
        if(rateType){
            int minimum=Integer.parseInt(intent.getStringExtra("minimum"));
            int maximum=Integer.parseInt(intent.getStringExtra("maximum"));
            ArrayList<HashMap<String, Object>> maps=myDataBase.searchByRate(minimum,maximum);
            ArrayList<HashMap<String,String>> information=new ArrayList<HashMap<String,String>>();
            for(int i=0;i<maps.size();i++){
                HashMap<String, Object> map=maps.get(i);
                HashMap<String, String> map2=new HashMap<String, String>();
                map2.put("first name",(String)map.get("first name"));
                map2.put("last name",(String)map.get("last name"));
                map2.put("service name",(String)map.get("service name"));
                map2.put("service rate",(String)map.get("service rate"));
                map2.put("rating",(String)map.get("rating"));
                information.add(map2);
                times.put(Integer.toString(i), (ArrayList<AvailabilityTime>)map.get("times"));
                accountMap.put(Integer.toString(i), new Account((String)map.get("first name"), (String)map.get("last name")));
                serviceMap.put(Integer.toString(i), new Service((String)map.get("service name"), Integer.parseInt((String)map.get("service rate"))));
            }
            searchAdapter=new SearchAdapter(this, information);
            listView.setAdapter(searchAdapter);
        }
    }

    public void bookBtn(View view){
        ArrayList<String> index=searchAdapter.getIndex();
        if(index.size()!=1){
            textView.setText("Please only choose one to book!");
        }
        else{
            Intent intent=new Intent(this, BookFinal.class);
            intent.putExtra("account", accountMap);
            intent.putExtra("index", index.get(0));
            intent.putExtra("times", times);
            intent.putExtra("services", serviceMap);
            startActivity(intent);
            finish();
        }
    }
}
