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
import com.example.bjzha.project.SuccessfulLogin;
import com.example.bjzha.project.serviceProvider.AvailabilityTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BookFinal extends AppCompatActivity {
    private TextView textView;
    private ListView listView;
    private BookAdapter bookAdapter;
    private ArrayList<AvailabilityTime> t;
    private MyDataBase myDataBase;
    private Account account;
    private Service service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_final);
        textView=(TextView)findViewById(R.id.textView);
        myDataBase=new MyDataBase(this);
        Intent intent=getIntent();
        String postion=intent.getStringExtra("index");
        HashMap<String, ArrayList<AvailabilityTime>> times=(HashMap<String, ArrayList<AvailabilityTime>>)intent.getSerializableExtra("times");
        HashMap<String, Account> accountMap=(HashMap<String, Account>)intent.getSerializableExtra("account");
        HashMap<String, Service> serviceMap=(HashMap<String, Service>)intent.getSerializableExtra("services");
        account=accountMap.get(postion);
        service=serviceMap.get(postion);
        t=times.get(postion);
        listView=(ListView)findViewById(R.id.listView);
        bookAdapter=new BookAdapter(this,t);
        listView.setAdapter(bookAdapter);
    }

    public void bookBtn(View view){
        boolean error=false;
        ArrayList<String> index=bookAdapter.getIndex();
        for(int i=0;i<index.size();i++){
            AvailabilityTime availabilityTime=t.get(Integer.parseInt(index.get(i)));
            if(availabilityTime.getBooked().equals("TRUE")){
                error=true;
                textView.setText("It has been booked!");
                break;
            }
        }
        if(!error){
            for(int i=0;i<index.size();i++){
                AvailabilityTime availabilityTime=t.get(Integer.parseInt(index.get(i)));
                availabilityTime.setBooked("TRUE");
                myDataBase.updateAvailability(account,availabilityTime,availabilityTime);
                myDataBase.addToHO(SuccessfulLogin.getAccount(), account, service, availabilityTime);
            }
            finish();
        }
    }
}
