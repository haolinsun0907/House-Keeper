package com.example.bjzha.project.serviceProvider;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.bjzha.project.Account;
import com.example.bjzha.project.MyDataBase;
import com.example.bjzha.project.R;
import com.example.bjzha.project.SuccessfulLogin;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Profile extends AppCompatActivity {
    private EditText addressText;
    private EditText phoneText;
    private EditText companyText;
    private EditText descriptionText;
    private TextView licencedText;
    private RadioGroup group;
    private MyDataBase myDataBase;
    private Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        addressText=(EditText)findViewById(R.id.addressText);
        phoneText=(EditText)findViewById(R.id.phoneText);
        companyText=(EditText)findViewById(R.id.companyText);
        descriptionText=(EditText)findViewById(R.id.descriptionText);
        licencedText=(TextView)findViewById(R.id.licencedTextView);
        group=(RadioGroup)findViewById(R.id.radioGroup);
        myDataBase=new MyDataBase(this);
        account=SuccessfulLogin.getAccount();
    }

    public void finishBtn(View view){
        String address=addressText.getText().toString();
        String phone=phoneText.getText().toString();
        String company=companyText.getText().toString();
        String description=descriptionText.getText().toString();
        int id=group.getCheckedRadioButtonId();
        boolean error=false;

        //address validation
        Pattern pattern=Pattern.compile("^\\d*$");
        Matcher matcher=pattern.matcher(address);
        if(address==null){
            addressText.setText(null);
            addressText.setHint("Address cannot be empty!");
            error=true;
        }
        else if(matcher.matches()){
            addressText.setText(null);
            addressText.setHint("Please enter a valid address!");
            error=true;
        }

        //phone number validation
        pattern=Pattern.compile("^\\d{3}-\\d{3}-\\d{4}$");
        matcher=pattern.matcher(phone);
        Pattern pattern2=Pattern.compile("^\\d{10}$");
        Matcher matcher2=pattern2.matcher(phone);
        if(phone==null){
            phoneText.setText(null);
            phoneText.setHint("Phone number cannot be empty!");
            error=true;
        }
        else if(!matcher.matches() && !matcher2.matches()){
            phoneText.setText(null);
            phoneText.setHint("Please enter a valid phone number!");
            error=true;
        }

        //name of the company validation
        pattern=Pattern.compile("^\\d*$");
        matcher=pattern.matcher(company);
        if(company==null){
            companyText.setText(null);
            companyText.setHint("Name of the company cannot be empty!");
            error=true;
        }
        else if(matcher.matches()){
            companyText.setText(null);
            companyText.setHint("Please enter a valid name of the company!");
            error=true;
        }

        //validation of licenced
        if(id==-1){
            licencedText.setText("Please select yer or no!");
            error=true;
        }

        if(!error){
            HashMap<String, String> informations=ServiceProvider.getInformations();
            if(informations.size()==0){
                informations.put("Address", address);
                informations.put("Phone number", phone);
                informations.put("Name of the company", company);
                informations.put("General description", description);
                String licenced="";
                if(id==R.id.yesBtn){
                    licenced="yes";
                }
                else if(id==R.id.noBtn){
                    licenced="no";
                }
                informations.put("Licenced", licenced);
                myDataBase.addProfile(account, informations);
            }
            else{
                informations.put("Address", address);
                informations.put("Phone number", phone);
                informations.put("Name of the company", company);
                informations.put("General description", description);
                String licenced="";
                if(id==R.id.yesBtn){
                    licenced="yes";
                }
                else if(id==R.id.noBtn){
                    licenced="no";
                }
                informations.put("Licenced", licenced);
                myDataBase.updateProfile(account, informations);
            }
            finish();
        }
    }
}
