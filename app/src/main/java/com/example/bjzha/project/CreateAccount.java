package com.example.bjzha.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateAccount extends AppCompatActivity {
    private EditText firstNameText;
    private EditText lastNameText;
    private EditText passwordText;
    private TextView textView;
    private RadioGroup group;
    private String firstName;
    private String lastName;
    private String password;
    private int id;
    private boolean error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        firstNameText=(EditText)findViewById(R.id.firstName);
        lastNameText=(EditText)findViewById(R.id.lastName);
        passwordText=(EditText)findViewById(R.id.password);
        textView=findViewById(R.id.textView);
        group=(RadioGroup)findViewById(R.id.group);
        error=false;
    }

    public void create(View view){
        error=false;
        id=group.getCheckedRadioButtonId();
        firstName=firstNameText.getText().toString();
        lastName=lastNameText.getText().toString();
        password=passwordText.getText().toString();
        firstNameValidation();
        lastNameValidation();
        passwordValidation();
        roleValidation();
        if(!error) {
            boolean exist = false;
            MyDataBase myDataBase=new MyDataBase(this);
            Account accountInDB=myDataBase.findAccount(firstName, lastName);
            if(accountInDB!=null){
                exist=true;
            }
            if (exist) {
                textView.setText("This account has been created");
                firstNameText.setText("");
                lastNameText.setText("");
                passwordText.setText("");
            }
            else {
                String role="";
                if(id==R.id.homeOwner){
                    role="home owner";
                }
                else if(id==R.id.serviceProvider) {
                    role = "service provider";
                }
                else if(id==R.id.admin){
                    role="administrator";
                }
                Account account = new Account(firstName, lastName, password, role);
                myDataBase.addAccount(account);
                finish();
            }
        }
    }

    public boolean firstNameValidation(){
        if(firstName==null || firstName.equals("")){
            firstNameText.setText("");
            firstNameText.setHint("Please enter your first name");
            error=true;
        }
        else{
            Pattern pattern=Pattern.compile("^[a-zA-Z]*$");
            Matcher matcher=pattern.matcher(firstName);
            if(!matcher.matches()){
                firstNameText.setText(null);
                firstNameText.setHint("Please enter a valid first name");
                error=true;
            }
        }
        return error;
    }

    public boolean lastNameValidation(){
        if(lastName==null || lastName.equals("")){
            lastNameText.setText("");
            lastNameText.setHint("Please enter your last name");
            error=true;
        }
        else{
            Pattern pattern=Pattern.compile("^[a-zA-Z]*$");
            Matcher matcher=pattern.matcher(lastName);
            if(!matcher.matches()){
                lastNameText.setText("");
                lastNameText.setHint("Please enter a valid last name");
                error=true;
            }
        }
        return error;
    }

    public boolean passwordValidation(){
        if(password==null || password.equals("")){
            passwordText.setText("");
            passwordText.setHint("Please enter your password");
            error=true;
        }
        return error;
    }

    public boolean roleValidation(){
        if(id==-1){
            textView.setText("Please select a role");
            error=true;
        }
        return error;
    }
}
