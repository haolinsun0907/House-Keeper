package com.example.bjzha.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;

import com.example.bjzha.project.Administrator.Administrator;
import com.example.bjzha.project.homeOwner.HomeOwner;
import com.example.bjzha.project.serviceProvider.ServiceProvider;

public class SuccessfulLogin extends AppCompatActivity {
    private static Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successful_login);
        account=(Account)getIntent().getSerializableExtra("Account");
        String firstName=account.getFirstName();
        String role=account.getRole();
        TextView textView=findViewById(R.id.textView);
        textView.setText("Welcome "+firstName+"!"+" You are logged as "+role);
    }

    public void logoutBtn(View view){
        finish();
    }

    public void continueBtn(View view){
        String role=account.getRole();
        if(role.equals("ADMINISTRATOR")){
            Intent intent=new Intent(this, Administrator.class);
            startActivity(intent);
        }
        else if(role.equals("service provider".toUpperCase())){
            Intent intent=new Intent(this, ServiceProvider.class);
            startActivity(intent);
        }
        else if(role.equals("HOME OWNER")){
            Intent intent=new Intent(this, HomeOwner.class);
            startActivity(intent);
        }
    }

    public static Account getAccount(){
        return account;
    }
}
