package com.example.bjzha.project;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bjzha.project.Administrator.Service;
import com.example.bjzha.project.serviceProvider.AvailabilityTime;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private MyDataBase myDataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDataBase=new MyDataBase(this);
        //Account account=new Account("admin","admin","admin","administrator");
        //myDataBase.addAccount(account);
        //Account account=new Account("a", "a", "a", "SERVICE PROVIDER");
        //ArrayList<AvailabilityTime> availabilityTimes=myDataBase.findAvailabilities(account);
        //textView.setText(Integer.toString(availabilityTimes.size()));
        //boolean b=myDataBase.deleteAccount("admin","admin");
        //textView.setText(Boolean.toString(b));
    }

    public void signIn(View view) {
        EditText userNameText = (EditText) findViewById(R.id.name);
        EditText passwordText = (EditText) findViewById(R.id.password);
        String[] userName = userNameText.getText().toString().split(" ");
        String firstName="";
        String lastName="";
        if(userName==null){
            userNameText.setHint("Your name cannot be empty!");
        }
        else if(userName.length==1 && userName[0].toUpperCase().equals("ADMIN")){
            firstName="admin";
            lastName="admin";
        }
        else if(userName.length==1){
            userNameText.setText(null);
            userNameText.setHint("Please enter a valid name");
        }
        else if(userName.length!=2){
            userNameText.setText(null);
            userNameText.setHint("Please enter a valid name");
            passwordText.setText(null);
        }
        else{
            firstName=userName[0].toUpperCase();
            lastName=userName[1].toUpperCase();
        }
        String password = passwordText.getText().toString();
        Account accountInDB=myDataBase.findAccount(firstName, lastName);
        if (accountInDB==null) {
            userNameText.setText(null);
            passwordText.setText(null);
            userNameText.setHint("Not a valid account!");
        }
        else if(!password.equals(accountInDB.getPassword())){
            passwordText.setText(null);
            passwordText.setHint("Not a valid password!");
            passwordText.setText(accountInDB.getPassword());
        }
        else {
            Intent intent = new Intent(this, SuccessfulLogin.class);
            intent.putExtra("Account", accountInDB);
            startActivity(intent);
            userNameText.setText(null);
            passwordText.setText(null);
            userNameText.setHint("Please enter your name");
            passwordText.setHint("Please enter your password");
        }
    }

    public void createAccount(View view){
        Intent intent=new Intent(this,CreateAccount.class);
        startActivity(intent);
    }
}
