package com.example.bjzha.project.serviceProvider;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bjzha.project.Administrator.Administrator;
import com.example.bjzha.project.Administrator.MyAdapter;
import com.example.bjzha.project.Administrator.Service;
import com.example.bjzha.project.MyDataBase;
import com.example.bjzha.project.R;
import com.example.bjzha.project.SuccessfulLogin;

import java.util.ArrayList;

public class ServiceProviderAdd extends AppCompatActivity {
    private ListView listView;
    private MyAdapter myAdapter;
    private MyDataBase myDataBase;
    private ArrayList<Service> services;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_add);
        myDataBase=new MyDataBase(this);
        listView=findViewById(R.id.list);
        services=myDataBase.getAllServices();
        myAdapter=new MyAdapter(this, services);
        listView.setAdapter(myAdapter);
    }

    public void addBtn(View view){
        ArrayList<String> index=myAdapter.getIndex();
        Boolean add=false;
        Boolean exist=false;
        for (int i=0;i<index.size();i++){
            int position=Integer.parseInt(index.get(i));
            Service service=services.get(position);
            for(int j=0;j<ServiceProvider.getServices().size();j++){
                if(service.getName().equals(ServiceProvider.getServices().get(j).getName())){
                    TextView textView=(TextView)findViewById(R.id.textView);
                    textView.setText("This service is already in your service list!");
                    exist=true;
                }
            }
            if(!exist) {
                myDataBase.addService(SuccessfulLogin.getAccount(),service);
                ServiceProvider.getServices().add(service);
                add=true;
            }
        }
        /*TextView textView=(TextView)findViewById(R.id.textView);
        textView.setText(Boolean.toString(exist));*/
        if(add){
            finish();
        }
    }
}
