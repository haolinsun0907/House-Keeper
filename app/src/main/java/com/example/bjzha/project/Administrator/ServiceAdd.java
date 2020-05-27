package com.example.bjzha.project.Administrator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.bjzha.project.Administrator.Administrator;
import com.example.bjzha.project.Administrator.Service;
import com.example.bjzha.project.Administrator.ServiceActivity;
import com.example.bjzha.project.R;

import java.util.ArrayList;

public class ServiceAdd extends AppCompatActivity {
    private RadioGroup group1,group2,group3,group4,group5,group6,group7;
    private RadioButton btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10,btn11,btn12,btn13;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_add);
        group1=(RadioGroup)findViewById(R.id.group1);
        group2=(RadioGroup)findViewById(R.id.group2);
        group3=(RadioGroup)findViewById(R.id.group3);
        group4=(RadioGroup)findViewById(R.id.group4);
        group5=(RadioGroup)findViewById(R.id.group5);
        group6=(RadioGroup)findViewById(R.id.group6);
        group7=(RadioGroup)findViewById(R.id.group7);
        btn0=(RadioButton)findViewById(R.id.applicanceInstall);
        btn1=(RadioButton)findViewById(R.id.carpetCleaning);
        btn2=(RadioButton)findViewById(R.id.moving);
        btn3=(RadioButton)findViewById(R.id.pluming);
        btn4 =(RadioButton)findViewById(R.id.applicanceRepair);
        btn5=(RadioButton)findViewById(R.id.furnitureAssembly);
        btn6=(RadioButton)findViewById(R.id.lockSmith);
        btn7=(RadioButton)findViewById(R.id.painting);
        btn8=(RadioButton)findViewById(R.id.windowCleanig);
        btn9=(RadioButton)findViewById(R.id.electrical);
        btn10=(RadioButton)findViewById(R.id.mouldRemediation);
        btn11=(RadioButton)findViewById(R.id.pestControl);
        btn12=(RadioButton)findViewById(R.id.junkRemoval);
        btn13=(RadioButton)findViewById(R.id.handymanService);
        btn0.setOnClickListener(new BtnSelected("1"));
        btn1.setOnClickListener(new BtnSelected("1"));
        btn2.setOnClickListener(new BtnSelected("2"));
        btn3.setOnClickListener(new BtnSelected("2"));
        btn4.setOnClickListener(new BtnSelected("3"));
        btn5.setOnClickListener(new BtnSelected("3"));
        btn6.setOnClickListener(new BtnSelected("4"));
        btn7.setOnClickListener(new BtnSelected("4"));
        btn8.setOnClickListener(new BtnSelected("5"));
        btn9.setOnClickListener(new BtnSelected("5"));
        btn10.setOnClickListener(new BtnSelected("6"));
        btn11.setOnClickListener(new BtnSelected("6"));
        btn12.setOnClickListener(new BtnSelected("7"));
        btn13.setOnClickListener(new BtnSelected("7"));
        setResult(RESULT_OK);
    }

    private class BtnSelected implements View.OnClickListener{
        private String id;
        private ArrayList<String> list;
        private ArrayList<RadioGroup> groups;

        private BtnSelected(String id){
            this.id=id;
            list=new ArrayList<String>();
            list.add("1");
            list.add("2");
            list.add("3");
            list.add("4");
            list.add("5");
            list.add("6");
            list.add("7");
            groups=new ArrayList<RadioGroup>();
            groups.add(group1);
            groups.add(group2);
            groups.add(group3);
            groups.add(group4);
            groups.add(group5);
            groups.add(group6);
            groups.add(group7);
        }

        @Override
        public void onClick(View view){
            for(int i=0;i<list.size();i++){
                if(!id.equals(list.get(i))){
                    RadioGroup group=groups.get(i);
                    group.clearCheck();
                }
            }
        }
    }

    public void addBtn(View view){
        int id1,id2,id3,id4,id5,id6,id7;
        id1=group1.getCheckedRadioButtonId();
        id2=group2.getCheckedRadioButtonId();
        id3=group3.getCheckedRadioButtonId();
        id4=group4.getCheckedRadioButtonId();
        id5=group5.getCheckedRadioButtonId();
        id6=group6.getCheckedRadioButtonId();
        id7=group7.getCheckedRadioButtonId();
        if(id1==-1 && id2==-1 && id3==-1 && id4==-1 && id5==-1 && id6==-1 && id7==-1){
            TextView textView=(TextView)findViewById(R.id.textView);
            textView.setText("Please select an existing service!");
        }
        else{
            String name="";
            int id=0;
            ArrayList<Integer> ids=new ArrayList<Integer>();
            ids.add(id1);
            ids.add(id2);
            ids.add(id3);
            ids.add(id4);
            ids.add(id5);
            ids.add(id6);
            ids.add(id7);
            for(int i=0;i<ids.size();i++){
                int selectedId=ids.get(i);
                if(selectedId!=-1){
                    int group=i+1;
                    if(group==1){
                        if(selectedId==R.id.applicanceInstall){
                            name="Applicance Install";
                            id=R.id.applicanceInstall;
                        }
                        else if(selectedId==R.id.carpetCleaning){
                            name="Carpet Cleaning";
                            id=R.id.carpetCleaning;
                        }
                    }
                    else if(group==2){
                        if(selectedId==R.id.moving){
                            name="Moving";
                            id=R.id.moving;
                        }
                        else if(selectedId==R.id.pluming){
                            name="Pluming";
                            id=R.id.pluming;
                        }
                    }
                    else if(group==3){
                        if(selectedId==R.id.applicanceRepair){
                            name="Applicance Repair";
                            id=R.id.applicanceRepair;
                        }
                        else if(selectedId==R.id.furnitureAssembly){
                            name="Furniture Assembly";
                            id=R.id.furnitureAssembly;
                        }
                    }
                    else if(group==4){
                        if(selectedId==R.id.lockSmith){
                            name="Lock Smith";
                            id=R.id.lockSmith;
                        }
                        else if(selectedId==R.id.painting){
                            name="Painting";
                            id=R.id.painting;
                        }
                    }
                    else if(group==5){
                        if(selectedId==R.id.windowCleanig){
                            name="Window Cleanig";
                            id=R.id.windowCleanig;
                        }
                        else if(selectedId==R.id.electrical){
                            name="Electrical";
                            id=R.id.electrical;
                        }
                    }
                    else if(group==6){
                        if(selectedId==R.id.mouldRemediation){
                            name="Mould Remediation";
                            id=R.id.mouldRemediation;
                        }
                        else if(selectedId==R.id.pestControl){
                            name="Pest Control";
                            id=R.id.pestControl;
                        }
                    }
                    else if(group==7){
                        if(selectedId==R.id.junkRemoval){
                            name="Junk Removal";
                            id=R.id.junkRemoval;
                        }
                        else if(selectedId==R.id.handymanService){
                            name="Handyman Service";
                            id=R.id.handymanService;
                        }
                    }
                }
            }
            ArrayList<Service> services=Administrator.getServices();
            boolean exist=false;
            for(int i=0;i<services.size();i++){
                String itemInServices=services.get(i).getName().toLowerCase();
                if(itemInServices.equals(name.toLowerCase())){
                    TextView textView=(TextView)findViewById(R.id.textView);
                    textView.setText(null);
                    textView.setHint("This service has already existed!");
                    exist=true;
                    break;
                }
            }
            if(!exist) {
                Intent intent = new Intent(this, ServiceActivity.class);
                intent.putExtra("Activity name", "add");
                intent.putExtra("Service name", name);
                intent.putExtra("Service id", id);
                startActivity(intent);
            }
        }
    }

    public void createBtn(View view){
        Intent intent=new Intent(this, ServiceActivity.class);
        intent.putExtra("Activity name", "create");
        startActivity(intent);
    }
}
