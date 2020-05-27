package com.example.bjzha.project.serviceProvider;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bjzha.project.Account;
import com.example.bjzha.project.Administrator.Service;
import com.example.bjzha.project.MyDataBase;
import com.example.bjzha.project.R;
import com.example.bjzha.project.SuccessfulLogin;

import java.util.ArrayList;
import java.util.HashMap;

public class ServiceProvider extends AppCompatActivity {
    private static HashMap<String, String> informations;
    private static ArrayList<Service> services;
    private static ArrayList<AvailabilityTime> availabilityTimes;
    private ListView serviceList;
    private ListView availabilityList;
    private ServiceAdapter serviceAdapter;
    private AvailabilityAdapter availabilityAdapter;
    private final int REQUEST_FUNCTIONS=1;
    private MyDataBase myDataBase;
    private Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider2);
        myDataBase=new MyDataBase(this);
        account=SuccessfulLogin.getAccount();
        informations=myDataBase.findProfile(account);
        availabilityTimes=myDataBase.findAvailabilities(account);
        serviceList=findViewById(R.id.serviceList);
        availabilityList=findViewById(R.id.availabilityList);
        refresh();
    }

    public void refresh(){
        services=myDataBase.findServices(account);
        serviceAdapter=new ServiceAdapter(this, services);
        serviceList.setAdapter(serviceAdapter);
        availabilityAdapter=new AvailabilityAdapter(this, availabilityTimes);
        availabilityList.setAdapter(availabilityAdapter);
        TextView addressText=(TextView)findViewById(R.id.address);
        TextView phoneText=(TextView)findViewById(R.id.phoneNumber);
        TextView companyText=(TextView)findViewById(R.id.company);
        TextView descriptionText=(TextView)findViewById(R.id.description);
        TextView licencedText=(TextView)findViewById(R.id.licenced);
        String address, phone, company, description, licenced;
        address=informations.get("Address");
        phone=informations.get("Phone number");
        company=informations.get("Name of the company");
        description=informations.get("General description");
        licenced=informations.get("Licenced");
        addressText.setText("address: "+address);
        phoneText.setText("phone number: "+phone);
        companyText.setText("name of the company: "+company);
        descriptionText.setText("general description: "+description);
        licencedText.setText("licenced: "+licenced);
    }

    public void continueBtn(View view){
        Intent intent=new Intent(this, Functions.class);
        startActivityForResult(intent, REQUEST_FUNCTIONS);
    }

    public static HashMap<String, String> getInformations(){
        return informations;
    }

    public static ArrayList<Service> getServices(){
        return services;
    }

    public static ArrayList<AvailabilityTime> getAvailability(){
        return availabilityTimes;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode==REQUEST_FUNCTIONS && resultCode==RESULT_OK){
            refresh();
        }
    }
}
