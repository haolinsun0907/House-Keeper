package com.example.bjzha.project.Administrator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bjzha.project.Administrator.Administrator;
import com.example.bjzha.project.Administrator.Service;
import com.example.bjzha.project.MyDataBase;
import com.example.bjzha.project.R;
import com.example.bjzha.project.SuccessfulLogin;

import java.util.ArrayList;

public class ServiceEdit extends AppCompatActivity {
    private Intent intent;
    private Bundle bd;
    private MyDataBase myDataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_edit);
        intent=getIntent();
        bd=intent.getExtras();
        myDataBase=new MyDataBase(this);
        Service service=(Service)bd.getSerializable("Service");
        String name=service.getName();
        TextView serviceName=(TextView)findViewById(R.id.name);
        serviceName.setText(name);
    }

    public void completeBtn(View view){
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
                if (rate < 0) {
                    rateText.setText(null);
                    rateText.setHint("Your service rate cannot be negative");
                } else {
                    Service oldService = (Service) bd.getSerializable("Service");
                    String name=oldService.getName();
                    Service newService= new Service(name, rate);
                    myDataBase.updateService(SuccessfulLogin.getAccount(),oldService,newService);
                    setResult(RESULT_OK);
                    finish();
                }
            }
        }
    }
}
