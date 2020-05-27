package com.example.bjzha.project.homeOwner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.bjzha.project.Account;
import com.example.bjzha.project.MyDataBase;
import com.example.bjzha.project.R;
import com.example.bjzha.project.SuccessfulLogin;

public class HomeOwner extends AppCompatActivity {
    private Account account;
    private MyDataBase myDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_owner);
        account=SuccessfulLogin.getAccount();
        myDataBase=new MyDataBase(this);
    }

    public void searchBtn(View view){
        Intent intent=new Intent(this, Search.class);
        startActivity(intent);
    }

    public void rateBtn(View view){
        Intent intent=new Intent(this, BookList.class);
        startActivity(intent);
    }
}
