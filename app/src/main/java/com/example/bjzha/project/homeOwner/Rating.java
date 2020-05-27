package com.example.bjzha.project.homeOwner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bjzha.project.Account;
import com.example.bjzha.project.Administrator.Service;
import com.example.bjzha.project.MyDataBase;
import com.example.bjzha.project.R;
import com.example.bjzha.project.SuccessfulLogin;
import com.example.bjzha.project.serviceProvider.AvailabilityTime;

import java.nio.file.attribute.AttributeView;

public class Rating extends AppCompatActivity {
    private Intent intent;
    private Service service;
    private AvailabilityTime availabilityTime;
    private EditText editText;
    private MyDataBase myDataBase;
    private Account account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        intent=getIntent();
        service=(Service)intent.getSerializableExtra("service");
        availabilityTime=(AvailabilityTime)intent.getSerializableExtra("time");
        account=(Account)intent.getSerializableExtra("spaccount");
        editText=(EditText)findViewById(R.id.ratingText);
    }

    public void finishBtn(View view){
        int rating=Integer.parseInt(editText.getText().toString());
        service.setRating(rating);
        availabilityTime.setBooked("FALSE");
        TextView textView=(TextView)findViewById(R.id.textView);
        textView.setText(availabilityTime.toString());
        //myDataBase.removeFromHO(SuccessfulLogin.getAccount(), service, availabilityTime);
        //myDataBase.updateAvailability(account,availabilityTime,availabilityTime);
        //finish();
    }
}
