package com.example.bjzha.project.Administrator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bjzha.project.Account;
import com.example.bjzha.project.Administrator.Administrator;
import com.example.bjzha.project.Administrator.Service;
import com.example.bjzha.project.MyDataBase;
import com.example.bjzha.project.R;
import com.example.bjzha.project.SuccessfulLogin;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServiceActivity extends AppCompatActivity {
    private String serviceName;
    private MyDataBase myDataBase;
    private Account account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        account=SuccessfulLogin.getAccount();
        myDataBase=new MyDataBase(this);
        Intent intent=getIntent();
        if(intent.getStringExtra("Activity name").equals("add")){
            EditText name=(EditText)findViewById(R.id.name);
            name.setEnabled(false);
            serviceName=intent.getStringExtra("Service name");
            name.setText(serviceName);
        }
    }

    public void add(View view){
        Intent intent=getIntent();
        //Create your own service
        if(intent.getStringExtra("Activity name").equals("create")){
            EditText name=(EditText)findViewById(R.id.name);
            serviceName=name.getText().toString();
            //service name is empty
            if(serviceName==null){
                name.setText(null);
                name.setHint("Your service name cannot be empty!");
                EditText rateText=(EditText)findViewById(R.id.rate);
                String rateString=rateText.getText().toString();
                //rate is empty
                if(rateString==null){
                    rateText.setText(null);
                    rateText.setHint("Your service hourly rate cannot be empty!");
                }
                else {
                    //rate is not a form of numbers
                    int rate = -1;
                    try {
                        rate = Integer.parseInt(rateString);
                    } catch (NumberFormatException e) {
                        rateText.setHint("Please enter a valid hourly rate");
                    }
                }
            }
            //service name is not empty
            else {
                //The service name cannot only contain numbers, but it can contain both characters and numbers
                Pattern pattern = Pattern.compile("[0123456789]*");
                Matcher matcher = pattern.matcher(serviceName);
                if(matcher.matches()){
                    name.setText(null);
                    name.setHint("Your service cannot only contain numbers!");
                    EditText rateText=(EditText)findViewById(R.id.rate);
                    String rateString=rateText.getText().toString();
                    if(rateString==null){
                        rateText.setText(null);
                        rateText.setHint("Your service hourly rate cannot be empty!");
                    }
                    else {
                        int rate = -1;
                        try {
                            rate = Integer.parseInt(rateString);
                        } catch (NumberFormatException e) {
                            rateText.setText(null);
                            rateText.setHint("Please enter a valid hourly rate");
                        }
                    }
                }
                else{
                    boolean exist=false;
                    Service serviceInDB=myDataBase.findService(account, serviceName);
                    if(serviceInDB!=null){
                        exist=true;
                        name.setText(null);
                        name.setHint("This service has already existed!");
                    }
                    if(!exist) {
                        EditText rateText = (EditText) findViewById(R.id.rate);
                        String rateString = rateText.getText().toString();
                        if (rateString == null) {
                            rateText.setText(null);
                            rateText.setHint("Your service hourly rate cannot be empty!");
                        } else {
                            int rate = -1;
                            boolean error = false;
                            try {
                                rate = Integer.parseInt(rateString);
                            } catch (NumberFormatException e) {
                                rateText.setText(null);
                                rateText.setHint("Please enter a valid hourly rate");
                                error = true;
                            }
                            if (!error) {
                                //rate cannot be a negative number, but it can be zero which means the service is free
                                if (rate < 0) {
                                    rateText.setText(null);
                                    rateText.setHint("Your service rate cannot be negative");
                                }
                                else if(rateText.getText().toString().length()>1 && rateText.getText().toString().substring(0,1).equals("0")){
                                    rateText.setText(null);
                                    rateText.setHint("Not a valid rate");
                                }
                                else {
                                    Service service = new Service(serviceName, rate);
                                    myDataBase.addService(account,service);
                                    finish();
                                }
                            }
                        }
                    }
                }
            }
        }
        //add an existing service
        else if(intent.getStringExtra("Activity name").equals("add")) {
            EditText rateText = (EditText) findViewById(R.id.rate);
            String rateString = rateText.getText().toString();
            if (rateString == null) {
                rateText.setText(null);
                rateText.setHint("Your service hourly rate cannot be empty!");
            } else {
                int rate = -1;
                boolean error = false;
                try {
                    rate = Integer.parseInt(rateString);
                } catch (NumberFormatException e) {
                    rateText.setText(null);
                    rateText.setHint("Please enter a valid hourly rate");
                    error = true;
                }
                if (!error) {
                    if(rate<0){
                        rateText.setText(null);
                        rateText.setHint("Your service rate cannot be negative");
                    }
                    else if(rateText.getText().toString().length()>1 && rateText.getText().toString().substring(0,1).equals("0")){
                        rateText.setText(null);
                        rateText.setHint("Not a valid rate");
                    }
                    else {
                        Service service = new Service(serviceName, rate);
                        myDataBase.addService(account,service);
                        finish();
                    }
                }
            }
        }
    }
}
