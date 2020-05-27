package com.example.bjzha.project.Administrator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bjzha.project.Account;
import com.example.bjzha.project.MyDataBase;
import com.example.bjzha.project.R;
import com.example.bjzha.project.SuccessfulLogin;

import java.util.ArrayList;

public class Administrator extends AppCompatActivity {
    private static ArrayList<Service> services;
    private ListView listView;
    private MyAdapter myAdapter;
    private Account account;
    private MyDataBase myDataBase;
    TextView tips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator);
        account=SuccessfulLogin.getAccount();
        listView=findViewById(R.id.list);
        myDataBase=new MyDataBase(this);
        services=myDataBase.findServices(account);
        tips=(TextView)findViewById(R.id.tips);
        refresh();
    }

    public void addBtn(View view){
        Intent intent=new Intent(this, ServiceAdd.class);
        startActivityForResult(intent, 1);
    }

    public void removeBtn(View view){
        ArrayList<String> index=myAdapter.getIndex();
        String s="";
        for(int i=0;i<index.size();i++){
            /*if(i==0){
                services.remove(Integer.parseInt(index.get(i)));
            }
            else{
                services.remove(Integer.parseInt(index.get(i))-i);
            }*/
            myDataBase.deleteService(account,services.get(Integer.parseInt(index.get(i))).getName());
        }
        refresh();
    }

    public void editBtn(View view){
        ArrayList<String> index=myAdapter.getIndex();
        if(index.size()>1 || index.size()==0){
            tips.setText("Please select only one service to edit");
        }
        else{
            Service service=services.get(Integer.parseInt(index.get(0)));
            Intent intent=new Intent(this, ServiceEdit.class);
            Bundle bd=new Bundle();
            bd.putSerializable("Service", service);
            bd.putInt("Position", Integer.parseInt(index.get(0)));
            intent.putExtras(bd);
            startActivityForResult(intent,2);
        }
    }

    public static ArrayList<Service> getServices(){
        return services;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode==RESULT_OK){
            refresh();
        }
    }

    public void refresh(){
        tips.setText(null);
        services=myDataBase.findServices(account);
        myAdapter=new MyAdapter(this, services);
        listView.setAdapter(myAdapter);
    }
}
