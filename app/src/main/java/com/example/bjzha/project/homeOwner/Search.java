package com.example.bjzha.project.homeOwner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bjzha.project.Account;
import com.example.bjzha.project.MyDataBase;
import com.example.bjzha.project.R;
import com.example.bjzha.project.SuccessfulLogin;
import com.example.bjzha.project.serviceProvider.AvailabilityTime;

import org.w3c.dom.Text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Search extends AppCompatActivity {
    private EditText serviceText,dateText,startHourText,endHourText,rateText,rateText2;
    private String serviceName,date;
    private int startHour,endHour,rate,rate2;
    private boolean error;
    private boolean serviceType, timeType, rateType;
    private TextView tips;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        serviceText=(EditText)findViewById(R.id.serviceText);
        dateText=(EditText)findViewById(R.id.dateText);
        startHourText=(EditText)findViewById(R.id.startHourText);
        endHourText=(EditText)findViewById(R.id.endHourText);
        rateText=(EditText)findViewById(R.id.rateText);
        rateText2=(EditText)findViewById(R.id.rateText2);
        tips=(TextView)findViewById(R.id.tips);
    }

    public void searchBtn(View view){
        error=false;
        serviceType=true;
        timeType=true;
        rateType=true;
        nameValidation();
        dateValidation();
        startHourValidation();
        endHourValidation();
        rate1Validation();
        rate2Validation();
        /*tips.setText(Boolean.toString(error));
        TextView textView=(TextView)findViewById(R.id.text);
        textView.setText(Boolean.toString(serviceType)+Boolean.toString(timeType)+Boolean.toString(rateType));*/
        if(!error){
            int numberOfType=0;
            if(serviceType){
                numberOfType++;
            }
            if(timeType){
                numberOfType++;
            }
            if (rateType) {
                numberOfType++;
            }
            if(numberOfType>1 || numberOfType==0){
                tips.setText("Error! Please fill only one search block");
            }
            else {
                Intent intent = new Intent(this, Book.class);
                intent.putExtra("service type", Boolean.toString(serviceType));
                intent.putExtra("time type", Boolean.toString(timeType));
                intent.putExtra("rate type", Boolean.toString(rateType));
                if (serviceType) {
                    intent.putExtra("service name", serviceName);
                }
                if (timeType) {
                    AvailabilityTime availabilityTime = new AvailabilityTime(date, startHour, 0, endHour, 0);
                    intent.putExtra("time", availabilityTime);
                }
                if (rateType) {
                    intent.putExtra("minimum", Integer.toString(rate));
                    intent.putExtra("maximum", Integer.toString(rate2));
                }
                startActivity(intent);
                finish();
            }
        }
    }

    public boolean nameValidation(){
        serviceName=serviceText.getText().toString();
        Pattern pattern=Pattern.compile("^\\d+$");
        Matcher matcher=pattern.matcher(serviceName);
        if(serviceName==null|| serviceName.equals("")){
            serviceType=false;
        }
        else if(matcher.matches()){
            serviceText.setText(null);
            serviceText.setHint("Please enter the valid service name");
            error=true;
            serviceType=false;
        }
        return error;
    }

    public boolean dateValidation(){
        date=dateText.getText().toString().toUpperCase();
        Pattern pattern=Pattern.compile("^\\d{1,}$");
        Matcher matcher=pattern.matcher(date);
        if(date==null || date.equals("")){
            timeType=false;
        }
        else if(matcher.matches()){
            dateText.setText(null);
            dateText.setHint("Please enter a valid date");
            error=true;
            timeType=false;
        }
        return !error;
    }

    public boolean startHourValidation(){
        if(startHourText.getText().toString()==null || startHourText.getText().toString().equals("")){
            timeType=false;
        }
        else {
            try {
                startHour = Integer.parseInt(startHourText.getText().toString());
            } catch (NumberFormatException e) {
                startHourText.setText(null);
                startHourText.setHint("Not a valid hour");
                error = true;
                timeType = false;
                return !error;
            }
            if (startHour > 23 || startHour < 0) {
                startHourText.setText(null);
                startHourText.setHint("Not a valid hour");
                error = true;
                timeType = false;
            }
            else if(startHourText.getText().toString().length()>1 && startHourText.getText().toString().substring(0,1).equals("0")){
                startHourText.setText(null);
                startHourText.setHint("Not a valid hour");
                error = true;
                timeType = false;
            }
            else if(startHourText.getText().toString().length()>2){
                startHourText.setText(null);
                startHourText.setHint("Not a valid hour");
                error = true;
                timeType = false;
            }
        }
        return !error;
    }

    public boolean endHourValidation(){
        if(endHourText.getText().toString()==null || endHourText.getText().toString().equals("")){
            timeType=false;
        }
        else {
            try {
                endHour = Integer.parseInt(endHourText.getText().toString());
            } catch (NumberFormatException e) {
                endHourText.setText(null);
                endHourText.setHint("Not a valid hour");
                timeType=false;
                error = true;
                return !error;
            }
            if(endHourText.getText().toString().length()>1 && endHourText.getText().toString().substring(0,1).equals("0")){
                endHourText.setText(null);
                endHourText.setHint("Not a valid hour");
                error = true;
                timeType = false;
            }
            else if(endHourText.getText().toString().length()>2){
                endHourText.setText(null);
                endHourText.setHint("Not a valid hour");
                error = true;
                timeType = false;
            }
            else if (endHour > 23 || endHour < 0 || endHour < startHour) {
                endHourText.setText(null);
                endHourText.setHint("Not a valid hour");
                timeType=false;
                error = true;
            }
        }
        return !error;
    }

    public boolean rate1Validation(){
        if(rateText.getText().toString()==null || rateText.getText().toString().equals("")){
            rateType=false;
        }
        else{
            try {
                rate = Integer.parseInt(rateText.getText().toString());
            } catch (NumberFormatException e) {
                rateText.setText(null);
                rateText.setHint("Please enter a valid hourly rate");
                error = true;
                rateType=false;
                return !error;
            }
            if(rateText.getText().toString().length()>1){
                rateText.setText(null);
                rateText.setHint("Not a valid rate");
                error = true;
                rateType = false;
            }
            else if(rate<1 || rate>5){
                rateText.setText(null);
                rateText.setHint("Your service rating must be 1-5");
                error=true;
                rateType=false;
            }
        }
        return !error;
    }

    public boolean rate2Validation(){
        if(rateText2.getText().toString()==null || rateText2.getText().toString().equals("")){
            rateType=false;
        }
        else{
            try {
                rate2 = Integer.parseInt(rateText2.getText().toString());
            } catch (NumberFormatException e) {
                rateText2.setText(null);
                rateText2.setHint("Please enter a valid hourly rate");
                error = true;
                rateType=false;
                return !error;
            }
            if(rateText2.getText().toString().length()>1){
                rateText2.setText(null);
                rateText2.setHint("Not a valid rate");
                error = true;
                rateType = false;
            }
            else if(rate2<1 || rate2>5){
                rateText2.setText(null);
                rateText2.setHint("Your service rating must be 1-5");
                error=true;
                rateType=false;
            }
            else if(rate2<rate){
                rateText2.setText(null);
                rateText2.setHint("Your maximum rate cannot be smaller than minimum rate");
                error=true;
                rateType=false;
            }
        }
        return !error;
    }
}