package com.example.bjzha.project.serviceProvider;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.example.bjzha.project.Account;
import com.example.bjzha.project.Administrator.MyAdapter;
import com.example.bjzha.project.Administrator.Service;
import com.example.bjzha.project.MyDataBase;
import com.example.bjzha.project.R;
import com.example.bjzha.project.SuccessfulLogin;

import java.util.ArrayList;

public class ServiceProviderDelete extends AppCompatActivity {
    private ListView listView;
    private ArrayList<Service> services;
    private MyAdapter myAdapter;
    private MyDataBase myDataBase;
    private Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_delete);
        listView=findViewById(R.id.list);
        myDataBase=new MyDataBase(this);
        account=SuccessfulLogin.getAccount();
        services=myDataBase.findServices(account);
        myAdapter=new MyAdapter(this, services);
        listView.setAdapter(myAdapter);
    }

    public void deleteBtn(View view){
        ArrayList<String> index=myAdapter.getIndex();
        for(int i=0;i<index.size();i++){
            if(i==0){
                ServiceProvider.getServices().remove(Integer.parseInt(index.get(i)));
            }
            else{
                ServiceProvider.getServices().remove(Integer.parseInt(index.get(i))-i);
            }
            myDataBase.deleteService(account,services.get(Integer.parseInt(index.get(i))).getName());
        }
        finish();
    }
}
