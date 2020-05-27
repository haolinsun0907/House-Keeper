package com.example.bjzha.project.serviceProvider;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.bjzha.project.R;

public class Functions extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_functions);
        setResult(RESULT_OK);
    }

    public void completeBtn(View view){
        Intent intent=new Intent(this, Profile.class);
        startActivity(intent);
    }

    public void addBtn(View view){
        Intent intent=new Intent(this, ServiceProviderAdd.class);
        startActivity(intent);
    }

    public void deleteBtn(View view){
        Intent intent=new Intent(this, ServiceProviderDelete.class);
        startActivity(intent);
    }

    public void specifyBtn(View view){
        Intent intent=new Intent(this, Availability.class);
        startActivity(intent);
    }

    public void modifyBtn(View view){
        Intent intent=new Intent(this, AvailabilityShow.class);
        startActivity(intent);
    }
}
