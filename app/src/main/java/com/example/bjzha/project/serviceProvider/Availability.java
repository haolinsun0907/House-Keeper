package com.example.bjzha.project.serviceProvider;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.bjzha.project.Account;
import com.example.bjzha.project.MyDataBase;
import com.example.bjzha.project.R;
import com.example.bjzha.project.SuccessfulLogin;

public class Availability extends AppCompatActivity {
    private TextView tips;
    private RadioGroup radioGroup;
    private int selecedId;
    private EditText startHourText, startMinText, endHourText, endMinText;
    private int startHour, startMin, endHour, endMin;
    private boolean error;
    private boolean modify;
    private Intent intent;
    private MyDataBase myDataBase;
    private Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_availability);
        tips=(TextView)findViewById(R.id.tips);
        tips.setTextSize(20);
        radioGroup=findViewById(R.id.group);
        startHourText=findViewById(R.id.startHour);
        startMinText=findViewById(R.id.startMin);
        endHourText=findViewById(R.id.endHour);
        endMinText=findViewById(R.id.endMin);
        myDataBase=new MyDataBase(this);
        account=SuccessfulLogin.getAccount();
        modify();
    }

    public boolean startHourValidation(){
        try{
            startHour=Integer.parseInt(startHourText.getText().toString());
        }
        catch (NumberFormatException e){
            startHourText.setText(null);
            startHourText.setHint("Not a valid hour");
            error=true;
        }
        if(startHour>23 || startHour<0){
            startHourText.setText(null);
            startHourText.setHint("Not a valid hour");
            error=true;
        }
        else if(startHourText.getText().toString().length()>1 && startHourText.getText().toString().substring(0,1).equals("0")){
            startHourText.setText(null);
            startHourText.setHint("Not a valid hour");
            error = true;
        }
        else if(startHourText.getText().toString().length()>2){
            startHourText.setText(null);
            startHourText.setHint("Not a valid hour");
            error = true;
        }
        return !error;
    }

    public boolean startMinValidation(){
        try{
            startMin=Integer.parseInt(startMinText.getText().toString());
        }
        catch (NumberFormatException e){
            startMinText.setText(null);
            startMinText.setHint("Not a valid minute");
            error=true;
        }
        if(startMin>59 || startHour<0){
            startMinText.setText(null);
            startMinText.setHint("Not a valid minute");
            error=true;
        }
        else if(startMinText.getText().toString().length()>1 && startMinText.getText().toString().substring(0,1).equals("0")){
            startMinText.setText(null);
            startMinText.setHint("Not a valid minute");
            error = true;
        }
        else if(startMinText.getText().toString().length()>2){
            startMinText.setText(null);
            startMinText.setHint("Not a valid minute");
            error = true;
        }
        return !error;
    }

    public boolean endHourValidation(){
        try{
            endHour=Integer.parseInt(endHourText.getText().toString());
        }
        catch (NumberFormatException e){
            endHourText.setText(null);
            endHourText.setHint("Not a valid hour");
            error=true;
        }
        if(endHour>23 || endHour<0 || endHour<startHour){
            endHourText.setText(null);
            endHourText.setHint("Not a valid hour");
            error=true;
        }
        else if(endHourText.getText().toString().length()>1 && endHourText.getText().toString().substring(0,1).equals("0")){
            endHourText.setText(null);
            endHourText.setHint("Not a valid hour");
            error = true;
        }
        else if(endHourText.getText().toString().length()>2){
            endHourText.setText(null);
            endHourText.setHint("Not a valid hour");
            error = true;
        }
        return !error;
    }

    public boolean endMinValidation(){
        try{
            endMin=Integer.parseInt(endMinText.getText().toString());
        }
        catch (NumberFormatException e){
            endMinText.setText(null);
            endMinText.setHint("Not a valid minute");
            error=true;
        }
        if(endMin>59 || endMin<0){
            endMinText.setText(null);
            endMinText.setHint("Not a valid minute");
            error=true;
        }
        else if(endMinText.getText().toString().length()>1 && endMinText.getText().toString().substring(0,1).equals("0")){
            endMinText.setText(null);
            endMinText.setHint("Not a valid minute");
            error = true;
        }
        else if(endMinText.getText().toString().length()>2){
            endMinText.setText(null);
            endMinText.setHint("Not a valid minute");
            error = true;
        }
        else if(startHour==endHour && endMin<=startMin){
            endMinText.setText(null);
            endMinText.setHint("Not a valid minute");
            error=true;
        }
        return !error;
    }

    public boolean buttonValidation(){
        selecedId=radioGroup.getCheckedRadioButtonId();
        if(selecedId==-1){
            tips.setText("Please select a date");
            error=true;
        }
        return error;
    }

    public boolean existValidation(){
        RadioButton radioButton=(RadioButton)findViewById(selecedId);
        String date=radioButton.getText().toString();
        AvailabilityTime availabilityTime=new AvailabilityTime(date, startHour, startMin, endHour, endMin);
        for(int i=0;i<ServiceProvider.getAvailability().size();i++){
            AvailabilityTime other=ServiceProvider.getAvailability().get(i);
            if(availabilityTime.equals(other)){
                tips.setText("This availability has already exsited!");
                error=true;
            }
            else{
                if(date.equals(other.getDate())) {
                    if ((startHour > other.getStartHour() && startHour < other.getEndHour()) || (endHour > other.getStartHour() && endHour < other.getEndHour())) {
                        tips.setText("Not a valid time");
                        error = true;
                    } else if (startHour == other.getEndHour()) {
                        if (startMin < other.getStartMin()) {
                            tips.setText("Not a valid time");
                            error = true;
                        }
                    } else if (endHour == other.getStartHour()) {
                        if (endMin > other.getEndMin()) {
                            tips.setText("Not a valid time");
                            error = true;
                        }
                    }
                }
            }
        }
        return !error;
    }

    public void modify(){
        intent=getIntent();
        if(intent.getStringExtra("Start hour")!=null){
            modify=true;
        }
        else{
            modify=false;
        }
        startHourText.setText(intent.getStringExtra("Start hour"));
        startMinText.setText(intent.getStringExtra("Start min"));
        endHourText.setText(intent.getStringExtra("End hour"));
        endMinText.setText(intent.getStringExtra("End min"));
    }

    public void finishBtn(View view){
        error=false;
        if(modify){
            tips.setText(intent.getStringExtra("Position"));
            AvailabilityTime oldTime=ServiceProvider.getAvailability().get(Integer.parseInt(intent.getStringExtra("Position")));
            startHourValidation();
            startMinValidation();
            endHourValidation();
            endMinValidation();
            buttonValidation();
            existValidation();
            if (!error) {
                RadioButton radioButton = (RadioButton) findViewById(selecedId);
                String date = radioButton.getText().toString().toUpperCase();
                AvailabilityTime availabilityTime = new AvailabilityTime(date, startHour, startMin, endHour, endMin);
                myDataBase.updateAvailability(account, oldTime, availabilityTime);
                ServiceProvider.getAvailability().remove(Integer.parseInt(intent.getStringExtra("Position")));
                ServiceProvider.getAvailability().add(Integer.parseInt(intent.getStringExtra("Position")), availabilityTime);
                setResult(RESULT_OK);
                finish();
            }
        }
        else {
            startHourValidation();
            startMinValidation();
            endHourValidation();
            endMinValidation();
            buttonValidation();
            existValidation();
            if (!error) {
                RadioButton radioButton = (RadioButton) findViewById(selecedId);
                String date = radioButton.getText().toString().toUpperCase();
                AvailabilityTime availabilityTime = new AvailabilityTime(date, startHour, startMin, endHour, endMin);
                myDataBase.addAvailability(account, availabilityTime);
                ServiceProvider.getAvailability().add(availabilityTime);
                finish();
            }
        }
    }
}
