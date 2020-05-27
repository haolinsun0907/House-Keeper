package com.example.bjzha.project;

import android.accounts.AccountAuthenticatorActivity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;

import com.example.bjzha.project.Administrator.Service;
import com.example.bjzha.project.serviceProvider.Availability;
import com.example.bjzha.project.serviceProvider.AvailabilityTime;

import java.util.ArrayList;
import java.util.HashMap;

public class MyDataBase extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 18;
    private static final String DATABASE_NAME = "projectDB.db";
    public static final String COLUMN_ID = "_id";
    //table accounts
    public static final String TABLE_ACCOUNTS = "accounts";
    public static final String COLUMN_FIRSTNAME = "firstname";
    public static final String COLUMN_LASTNAME="lastname";
    public static final String COLUMN_PASSWARD="password";
    public static final String COLUMN_ROLE="role";

    //table admin services
    public static final String TABLE_SERVICES_ADMIN="servicesAdmin";
    public static final String COLUMN_SERVICENAME="servicename";
    public static final String COLUMN_SERVICERATE="servicerate";

    //table service provider services
    public static final String TABLE_SERVICES_SV="servicesSV";
    public static final String COLUMN_RATING="rating";

    //table service provider profile
    public static final String TABLE_PROFILE_SV="profileSV";
    public static final String COLUMN_ADDRESS="address";
    public static final String COLUMN_PHONE="phone";
    public static final String COLUMN_COMPANY="company";
    public static final String COLUMN_DESCRIPTION="description";
    public static final String COLUMN_LICENCED="licenced";

    //table service provider availability
    public static final String TABLE_AVAILABILITY_SV="availabilitySV";
    public static final String COLUMN_STARTHOUR="starthour";
    public static final String COLUMN_STARTMIN="startmin";
    public static final String COLUMN_ENDHOUR="endhour";
    public static final String COLUMN_ENDMIN="endmin";
    public static final String COLUMN_DATE="date";

    public static final String TABLE_AVAILABILITY_BOOK="availabilityBook";
    public static final String COLUMN_BOOKED="booked";

    public static final String TABLE_RATE="rateTable";
    public static final String COLUMN_SPFIRSTNAME="spfirstName";
    public static final String COLUMN_SPLASTNAME="splastName";

    public MyDataBase (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        //create accounts table
        String CREATE_ACCOUNTS_TABLE= "CREATE TABLE "+TABLE_ACCOUNTS+"("
                +COLUMN_ID+" INTEGER PRIMARY KEY,"
                +COLUMN_FIRSTNAME+" TEXT,"
                +COLUMN_LASTNAME+" TEXT,"
                +COLUMN_PASSWARD+" TEXT,"
                +COLUMN_ROLE+" TEXT,"
                +COLUMN_SERVICENAME+" TEXT,"
                +COLUMN_SERVICERATE+" INTEGER"
                +")";
        db.execSQL(CREATE_ACCOUNTS_TABLE);

        //creates services admin table
        String CREATE_SERVICES_ADMIN_TABLE= "CREATE TABLE "+TABLE_SERVICES_ADMIN+"("
                +COLUMN_ID+" INTEGER PRIMARY KEY,"
                +COLUMN_FIRSTNAME+" TEXT,"
                +COLUMN_LASTNAME+" TEXT,"
                +COLUMN_SERVICENAME+" TEXT,"
                +COLUMN_SERVICERATE+" INTEGER"
                +")";
        db.execSQL(CREATE_SERVICES_ADMIN_TABLE);

        //create services SV table
        String CREATE_SERVICES_SV_TABLE= "CREATE TABLE "+TABLE_SERVICES_SV+"("
                +COLUMN_ID+" INTEGER PRIMARY KEY,"
                +COLUMN_FIRSTNAME+" TEXT,"
                +COLUMN_LASTNAME+" TEXT,"
                +COLUMN_SERVICENAME+" TEXT,"
                +COLUMN_SERVICERATE+" INTEGER,"
                +COLUMN_RATING+" INTEGER"
                +")";
        db.execSQL(CREATE_SERVICES_SV_TABLE);

        //create service provider profile table
        String CREATE_PROFILE_SV_TABLE="CREATE TABLE "+TABLE_PROFILE_SV+"("
                +COLUMN_ID+" INTEGER PRIMARY KEY,"
                +COLUMN_FIRSTNAME+" TEXT,"
                +COLUMN_LASTNAME+" TEXT,"
                +COLUMN_ADDRESS+" TEXT,"
                +COLUMN_PHONE+" TEXT,"
                +COLUMN_COMPANY+" TEXT,"
                +COLUMN_DESCRIPTION+" TEXT,"
                +COLUMN_LICENCED+" TEXT"
                +")";
        db.execSQL(CREATE_PROFILE_SV_TABLE);

        //create service provider availability table
        String CREATE_AVAILABILITY_SV_TABLE="CREATE TABLE "+TABLE_AVAILABILITY_SV+"("
                +COLUMN_ID+" INTEGER PRIMARY KEY,"
                +COLUMN_FIRSTNAME+" TEXT,"
                +COLUMN_LASTNAME+" TEXT,"
                +COLUMN_DATE+" TEXT,"
                +COLUMN_STARTHOUR+" INTEGER,"
                +COLUMN_STARTMIN+" INTEGER,"
                +COLUMN_ENDHOUR+" INTEGER,"
                +COLUMN_ENDMIN+" INTEGER"
                +")";
        db.execSQL(CREATE_AVAILABILITY_SV_TABLE);

        String CREATE_AVAILABILITY_BOOK_TABLE="CREATE TABLE "+TABLE_AVAILABILITY_BOOK+"("
                +COLUMN_ID+" INTEGER PRIMARY KEY,"
                +COLUMN_FIRSTNAME+" TEXT,"
                +COLUMN_LASTNAME+" TEXT,"
                +COLUMN_DATE+" TEXT,"
                +COLUMN_STARTHOUR+" INTEGER,"
                +COLUMN_STARTMIN+" INTEGER,"
                +COLUMN_ENDHOUR+" INTEGER,"
                +COLUMN_ENDMIN+" INTEGER,"
                +COLUMN_BOOKED+" TEXT"
                +")";
        db.execSQL(CREATE_AVAILABILITY_BOOK_TABLE);

        String CREATE_RATE_TABLE="CREATE TABLE "+TABLE_RATE+"("
                +COLUMN_ID+" INTEGER PRIMARY KEY,"
                +COLUMN_FIRSTNAME+" TEXT,"
                +COLUMN_LASTNAME+" TEXT,"
                +COLUMN_SERVICENAME+" TEXT,"
                +COLUMN_SERVICERATE+" INTEGER,"
                +COLUMN_DATE+" TEXT,"
                +COLUMN_STARTHOUR+" INTEGER,"
                +COLUMN_STARTMIN+" INTEGER,"
                +COLUMN_ENDHOUR+" INTEGER,"
                +COLUMN_ENDMIN+" INTEGER,"
                +COLUMN_RATING+" INTEGER,"
                +COLUMN_SPFIRSTNAME+" TEXT,"
                +COLUMN_SPLASTNAME+" TEXT"
                +")";
        db.execSQL(CREATE_RATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_ACCOUNTS);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_SERVICES_ADMIN);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_SERVICES_SV);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_PROFILE_SV);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_AVAILABILITY_SV);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_AVAILABILITY_BOOK);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_RATE);
        onCreate(db);
    }

    public void addAccount(Account account){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COLUMN_FIRSTNAME, account.getFirstName());
        contentValues.put(COLUMN_LASTNAME, account.getLastName());
        contentValues.put(COLUMN_PASSWARD, account.getPassword());
        contentValues.put(COLUMN_ROLE, account.getRole());
        db.insert(TABLE_ACCOUNTS, null, contentValues);
        db.close();
    }

    public Account findAccount(String firstName, String lastName){
        SQLiteDatabase db=this.getReadableDatabase();
        String query="Select * FROM "+TABLE_ACCOUNTS+" WHERE "+COLUMN_FIRSTNAME+"=\""+firstName.toUpperCase()+"\""
                +" AND "+COLUMN_LASTNAME+"=\""+lastName.toUpperCase()+"\"";
        Cursor cursor=db.rawQuery(query,null);
        Account account=new Account();
        if(cursor.moveToFirst()){
            account.setFirstName(cursor.getString(1));
            account.setLastName(cursor.getString(2));
            account.setPassword(cursor.getString(3));
            account.setRole(cursor.getString(4));
            cursor.close();
        }
        else{
            account=null;
        }
        db.close();
        return account;
    }

    public boolean deleteAccount(String firstName, String lastName){
        boolean result=false;
        String query="Select * FROM "+TABLE_ACCOUNTS+" WHERE "+COLUMN_FIRSTNAME+"=\""+firstName.toUpperCase()+"\""
                +" AND "+COLUMN_LASTNAME+"=\""+lastName.toUpperCase()+"\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            String idStr = cursor.getString(0);
            db.delete(TABLE_ACCOUNTS, COLUMN_ID + " = " + idStr, null);
            result = true;
        }
        //delete from table services_admin
        query="Select * FROM "+TABLE_SERVICES_ADMIN+" WHERE "+COLUMN_FIRSTNAME+"=\""+firstName.toUpperCase()+"\""
                +" AND "+COLUMN_LASTNAME+"=\""+lastName.toUpperCase()+"\"";
        cursor=db.rawQuery(query, null);
        while (cursor.moveToNext()){
            String idStr = cursor.getString(0);
            db.delete(TABLE_SERVICES_ADMIN, COLUMN_ID + " = " + idStr, null);
        }
        //delete from table services_sv
        query="Select * FROM "+TABLE_SERVICES_SV+" WHERE "+COLUMN_FIRSTNAME+"=\""+firstName.toUpperCase()+"\""
                +" AND "+COLUMN_LASTNAME+"=\""+lastName.toUpperCase()+"\"";
        cursor=db.rawQuery(query, null);
        while (cursor.moveToNext()){
            String idStr = cursor.getString(0);
            db.delete(TABLE_SERVICES_SV, COLUMN_ID + " = " + idStr, null);
        }
        //delete from table profile_sv
        query="Select * FROM "+TABLE_PROFILE_SV+" WHERE "+COLUMN_FIRSTNAME+"=\""+firstName.toUpperCase()+"\""
                +" AND "+COLUMN_LASTNAME+"=\""+lastName.toUpperCase()+"\"";
        cursor=db.rawQuery(query, null);
        while (cursor.moveToNext()){
            String idStr = cursor.getString(0);
            db.delete(TABLE_PROFILE_SV, COLUMN_ID + " = " + idStr, null);
        }
        //delete from table availability_sv
        query="Select * FROM "+TABLE_AVAILABILITY_SV+" WHERE "+COLUMN_FIRSTNAME+"=\""+firstName.toUpperCase()+"\""
                +" AND "+COLUMN_LASTNAME+"=\""+lastName.toUpperCase()+"\"";
        cursor=db.rawQuery(query, null);
        while (cursor.moveToNext()){
            String idStr = cursor.getString(0);
            db.delete(TABLE_AVAILABILITY_SV, COLUMN_ID + " = " + idStr, null);
        }
        cursor.close();
        db.close();
        return result;
    }

    public void addService(Account account, Service service){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COLUMN_FIRSTNAME, account.getFirstName());
        contentValues.put(COLUMN_LASTNAME, account.getLastName());
        contentValues.put(COLUMN_SERVICENAME, service.getName());
        contentValues.put(COLUMN_SERVICERATE, service.getRate());
        if(account.getRole().equals("ADMINISTRATOR")){
            db.insert(TABLE_SERVICES_ADMIN, null, contentValues);
        }
        else if(account.getRole().equals("SERVICE PROVIDER")){
            db.insert(TABLE_SERVICES_SV, null, contentValues);
        }
        db.close();
    }

    public Service findService(Account account, String serviceName){
        SQLiteDatabase db=this.getReadableDatabase();
        Service service=new Service();
        if(account.getRole().equals("ADMINISTRATOR")){
            String query="Select * FROM "+TABLE_SERVICES_ADMIN+" WHERE "+COLUMN_FIRSTNAME+"=\""+account.getFirstName()+"\""
                    +" AND "+COLUMN_LASTNAME+"=\""+account.getLastName()+"\""
                    +" AND "+COLUMN_SERVICENAME+"=\""+serviceName.toUpperCase()+"\"";
            Cursor cursor=db.rawQuery(query, null);
            if(cursor.moveToFirst()){
                service.setName(cursor.getString(3));
                service.setRate(Integer.parseInt(cursor.getString(4)));
                cursor.close();
            }
            else{
                service=null;
            }
        }
        else if(account.getRole().equals("SERVICE PROVIDER")){
            String query="Select * FROM "+TABLE_SERVICES_SV+" WHERE "+COLUMN_FIRSTNAME+"=\""+account.getFirstName()+"\""
                    +" AND "+COLUMN_LASTNAME+"=\""+account.getLastName()+"\""
                    +" AND "+COLUMN_SERVICENAME+"=\""+serviceName.toUpperCase()+"\"";
            Cursor cursor=db.rawQuery(query, null);
            if(cursor.moveToFirst()){
                service.setName(cursor.getString(3));
                service.setRate(Integer.parseInt(cursor.getString(4)));
                cursor.close();
            }
            else{
                service=null;
            }
        }
        db.close();
        return service;
    }

    public ArrayList<Service> findServices(Account account){
        SQLiteDatabase db=this.getReadableDatabase();
        ArrayList<Service> services=new ArrayList<Service>();
        if(account.getRole().equals("ADMINISTRATOR")){
            String query="Select * FROM "+TABLE_SERVICES_ADMIN+" WHERE "+COLUMN_FIRSTNAME+"=\""+account.getFirstName()+"\""
                    +" AND "+COLUMN_LASTNAME+"=\""+account.getLastName()+"\"";
            Cursor cursor=db.rawQuery(query, null);
            while (cursor.moveToNext()) {
                Service service=new Service();
                service.setName(cursor.getString(3));
                service.setRate(Integer.parseInt(cursor.getString(4)));
                services.add(service);
            }
            cursor.close();
        }
        else if(account.getRole().equals("SERVICE PROVIDER")){
            String query="Select * FROM "+TABLE_SERVICES_SV+" WHERE "+COLUMN_FIRSTNAME+"=\""+account.getFirstName()+"\""
                    +" AND "+COLUMN_LASTNAME+"=\""+account.getLastName()+"\"";
            Cursor cursor=db.rawQuery(query, null);
            while (cursor.moveToNext()) {
                Service service=new Service();
                service.setName(cursor.getString(3));
                service.setRate(Integer.parseInt(cursor.getString(4)));
                services.add(service);
            }
            cursor.close();
        }
        db.close();
        return services;
    }

    public boolean deleteService(Account account, String serviceName){
        SQLiteDatabase db=this.getWritableDatabase();
        boolean result=false;
        if(account.getRole().equals("ADMINISTRATOR")){
            String query="Select * FROM "+TABLE_SERVICES_ADMIN+" WHERE "+COLUMN_FIRSTNAME+"=\""+account.getFirstName()+"\""
                    +" AND "+COLUMN_LASTNAME+"=\""+account.getLastName()+"\""
                    +" AND "+COLUMN_SERVICENAME+"=\""+serviceName.toUpperCase()+"\"";
            Cursor cursor=db.rawQuery(query, null);
            if(cursor.moveToFirst()){
                String idStr=cursor.getString(0);
                db.delete(TABLE_SERVICES_ADMIN, COLUMN_ID+"="+idStr, null);
                cursor.close();
                result=true;
            }
        }
        else if(account.getRole().equals("SERVICE PROVIDER")){
            String query="Select * FROM "+TABLE_SERVICES_SV+" WHERE "+COLUMN_FIRSTNAME+"=\""+account.getFirstName()+"\""
                    +" AND "+COLUMN_LASTNAME+"=\""+account.getLastName()+"\""
                    +" AND "+COLUMN_SERVICENAME+"=\""+serviceName.toUpperCase()+"\"";
            Cursor cursor=db.rawQuery(query, null);
            if(cursor.moveToFirst()){
                String idStr=cursor.getString(0);
                db.delete(TABLE_SERVICES_SV, COLUMN_ID+"="+idStr, null);
                cursor.close();
                result=true;
            }
        }
        db.close();
        return result;
    }

    public boolean updateService(Account account,Service oldService, Service newService){
        boolean result=false;
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COLUMN_FIRSTNAME, account.getFirstName());
        contentValues.put(COLUMN_LASTNAME, account.getLastName());
        contentValues.put(COLUMN_SERVICENAME, newService.getName());
        contentValues.put(COLUMN_SERVICERATE, newService.getRate());
        String query="Select * FROM "+TABLE_SERVICES_ADMIN+" WHERE "+COLUMN_FIRSTNAME+"=\""+account.getFirstName()+"\""
                +" AND "+COLUMN_LASTNAME+"=\""+account.getLastName()+"\""
                +" AND "+COLUMN_SERVICENAME+"=\""+oldService.getName()+"\"";
        Cursor cursor=db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            String idStr=cursor.getString(0);
            db.update(TABLE_SERVICES_ADMIN, contentValues, COLUMN_ID+"="+idStr, null);
            result=true;
            cursor.close();
        }
        db.close();
        return result;
    }

    public ArrayList<Service> getAllServices(){
        SQLiteDatabase db=this.getReadableDatabase();
        String query="Select t1.servicename, t1.servicerate FROM "+TABLE_SERVICES_ADMIN+" t1, "+TABLE_ACCOUNTS+" t2"
                +" WHERE t1.firstname = t2.firstname AND t1.lastname=t2.lastname AND t2.role"+"=\""+"ADMINISTRATOR"+"\"";
        Cursor cursor=db.rawQuery(query,null);
        ArrayList<Service> services=new ArrayList<Service>();
        while (cursor.moveToNext()){
            Service service=new Service();
            service.setName(cursor.getString(0));
            service.setRate(Integer.parseInt(cursor.getString(1)));
            services.add(service);
        }
        cursor.close();
        db.close();
        return services;
    }

    public void addProfile(Account account, HashMap<String ,String> informations){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COLUMN_FIRSTNAME, account.getFirstName());
        contentValues.put(COLUMN_LASTNAME, account.getLastName());
        contentValues.put(COLUMN_ADDRESS, informations.get("Address"));
        contentValues.put(COLUMN_PHONE, informations.get("Phone number"));
        contentValues.put(COLUMN_COMPANY, informations.get("Name of the company"));
        contentValues.put(COLUMN_DESCRIPTION, informations.get("General description"));
        contentValues.put(COLUMN_LICENCED, informations.get("Licenced"));
        db.insert(TABLE_PROFILE_SV, null, contentValues);
        db.close();
    }

    public HashMap<String, String> findProfile(Account account){
        SQLiteDatabase db=this.getReadableDatabase();
        String query="Select * FROM "+TABLE_PROFILE_SV+" WHERE "+COLUMN_FIRSTNAME+"=\""+account.getFirstName()+"\""
                +" AND "+COLUMN_LASTNAME+"=\""+account.getLastName()+"\"";
        HashMap<String, String> informations=new HashMap<String, String>();
        Cursor cursor=db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            informations.put("Address", cursor.getString(3));
            informations.put("Phone number", cursor.getString(4));
            informations.put("Name of the company", cursor.getString(5));
            informations.put("General description", cursor.getString(6));
            informations.put("Licenced", cursor.getString(7));
        }
        cursor.close();
        db.close();
        return informations;
    }

    public boolean updateProfile(Account account, HashMap<String ,String> informations){
        boolean result=false;
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COLUMN_ADDRESS, informations.get("Address"));
        contentValues.put(COLUMN_PHONE, informations.get("Phone number"));
        contentValues.put(COLUMN_COMPANY, informations.get("Name of the company"));
        contentValues.put(COLUMN_DESCRIPTION, informations.get("General description"));
        contentValues.put(COLUMN_LICENCED, informations.get("Licenced"));
        String query="Select * FROM "+TABLE_PROFILE_SV+" WHERE "+COLUMN_FIRSTNAME+"=\""+account.getFirstName()+"\""
                +" AND "+COLUMN_LASTNAME+"=\""+account.getLastName()+"\"";
        Cursor cursor=db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            String idStr=cursor.getString(0);
            db.update(TABLE_PROFILE_SV, contentValues, COLUMN_ID+"="+idStr, null);
            result=true;
            cursor.close();
        }
        db.close();
        return result;
    }

    public void addAvailability(Account account, AvailabilityTime availabilityTime){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COLUMN_FIRSTNAME, account.getFirstName());
        contentValues.put(COLUMN_LASTNAME, account.getLastName());
        contentValues.put(COLUMN_DATE, availabilityTime.getDate());
        contentValues.put(COLUMN_STARTHOUR, availabilityTime.getStartHour());
        contentValues.put(COLUMN_STARTMIN, availabilityTime.getStartMin());
        contentValues.put(COLUMN_ENDHOUR, availabilityTime.getEndHour());
        contentValues.put(COLUMN_ENDMIN, availabilityTime.getEndMin());
        db.insert(TABLE_AVAILABILITY_SV, null, contentValues);
        contentValues.put(COLUMN_BOOKED, availabilityTime.getBooked());
        db.insert(TABLE_AVAILABILITY_BOOK, null, contentValues);
        db.close();
    }

    public ArrayList<AvailabilityTime> findAvailabilities(Account account){
        SQLiteDatabase db=this.getReadableDatabase();
        String query="Select * FROM "+TABLE_AVAILABILITY_SV+" WHERE "+COLUMN_FIRSTNAME+"=\""+account.getFirstName()+"\""
                +" AND "+COLUMN_LASTNAME+"=\""+account.getLastName()+"\"";
        Cursor cursor=db.rawQuery(query, null);
        ArrayList<AvailabilityTime> availabilityTimes=new ArrayList<AvailabilityTime>();
        while (cursor.moveToNext()){
            AvailabilityTime availabilityTime=new AvailabilityTime();
            availabilityTime.setDate(cursor.getString(3));
            availabilityTime.setStartHour(Integer.parseInt(cursor.getString(4)));
            availabilityTime.setStartMin(Integer.parseInt(cursor.getString(5)));
            availabilityTime.setEndHour(Integer.parseInt(cursor.getString(6)));
            availabilityTime.setEndMin(Integer.parseInt(cursor.getString(7)));
            availabilityTimes.add(availabilityTime);
        }
        cursor.close();
        db.close();
        return availabilityTimes;
    }

    public ArrayList<AvailabilityTime> findAvailabilitiesBooked(Account account){
        SQLiteDatabase db=this.getReadableDatabase();
        String query="Select * FROM "+TABLE_AVAILABILITY_BOOK+" WHERE "+COLUMN_FIRSTNAME+"=\""+account.getFirstName()+"\""
                +" AND "+COLUMN_LASTNAME+"=\""+account.getLastName()+"\"";
        Cursor cursor=db.rawQuery(query, null);
        ArrayList<AvailabilityTime> availabilityTimes=new ArrayList<AvailabilityTime>();
        while (cursor.moveToNext()){
            AvailabilityTime availabilityTime=new AvailabilityTime();
            availabilityTime.setDate(cursor.getString(3));
            availabilityTime.setStartHour(Integer.parseInt(cursor.getString(4)));
            availabilityTime.setStartMin(Integer.parseInt(cursor.getString(5)));
            availabilityTime.setEndHour(Integer.parseInt(cursor.getString(6)));
            availabilityTime.setEndMin(Integer.parseInt(cursor.getString(7)));
            availabilityTime.setBooked(cursor.getString(8));
            availabilityTimes.add(availabilityTime);
        }
        cursor.close();
        db.close();
        return availabilityTimes;
    }

    public boolean updateAvailability(Account account, AvailabilityTime oldTime, AvailabilityTime newTime){
        boolean result=false;
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COLUMN_FIRSTNAME, account.getFirstName());
        contentValues.put(COLUMN_LASTNAME, account.getLastName());
        contentValues.put(COLUMN_DATE, newTime.getDate());
        contentValues.put(COLUMN_STARTHOUR, newTime.getStartHour());
        contentValues.put(COLUMN_STARTMIN, newTime.getStartMin());
        contentValues.put(COLUMN_ENDHOUR, newTime.getEndHour());
        contentValues.put(COLUMN_ENDMIN, newTime.getEndMin());
        String query="Select * FROM "+TABLE_AVAILABILITY_SV+" WHERE "+COLUMN_FIRSTNAME+"=\""+account.getFirstName()+"\""
                +" AND "+COLUMN_LASTNAME+"=\""+account.getLastName()+"\""
                +" AND "+COLUMN_DATE+"=\""+oldTime.getDate()+"\""
                +" AND "+COLUMN_STARTHOUR+"=\""+oldTime.getStartHour()+"\""
                +" AND "+COLUMN_STARTMIN+"=\""+oldTime.getStartMin()+"\""
                +" AND "+COLUMN_ENDHOUR+"=\""+oldTime.getEndHour()+"\""
                +" AND "+COLUMN_ENDMIN+"=\""+oldTime.getEndMin()+"\"";
        Cursor cursor=db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            String idStr=cursor.getString(0);
            db.update(TABLE_AVAILABILITY_SV, contentValues, COLUMN_ID+"="+idStr, null);
            result=true;
        }
        query="Select * FROM "+TABLE_AVAILABILITY_BOOK+" WHERE "+COLUMN_FIRSTNAME+"=\""+account.getFirstName()+"\""
                +" AND "+COLUMN_LASTNAME+"=\""+account.getLastName()+"\""
                +" AND "+COLUMN_DATE+"=\""+oldTime.getDate()+"\""
                +" AND "+COLUMN_STARTHOUR+"=\""+oldTime.getStartHour()+"\""
                +" AND "+COLUMN_STARTMIN+"=\""+oldTime.getStartMin()+"\""
                +" AND "+COLUMN_ENDHOUR+"=\""+oldTime.getEndHour()+"\""
                +" AND "+COLUMN_ENDMIN+"=\""+oldTime.getEndMin()+"\"";
        cursor=db.rawQuery(query, null);
        contentValues.put(COLUMN_BOOKED, newTime.getBooked());
        if(cursor.moveToFirst()){
            String idStr=cursor.getString(0);
            db.update(TABLE_AVAILABILITY_BOOK, contentValues, COLUMN_ID+"="+idStr, null);
            result=true;
            cursor.close();
        }
        db.close();
        return result;
    }

    public ArrayList<HashMap<String, Object>> searchByService(String serviceName){
        ArrayList<HashMap<String, Object>> informations=new ArrayList<HashMap<String, Object>>();
        SQLiteDatabase db=this.getReadableDatabase();
        String query="Select * FROM servicesSV WHERE servicename"+"=\""+serviceName.toUpperCase()+"\"";
        Cursor cursor=db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String, Object> map=new HashMap<String, Object>();
            map.put("first name", cursor.getString(1));
            map.put("last name", cursor.getString(2));
            map.put("service name", cursor.getString(3));
            map.put("service rate", cursor.getString(4));
            map.put("rating", cursor.getString(5));
            informations.add(map);
        }
        for(int i=0;i<informations.size();i++){
            String currentFN=(String)informations.get(i).get("first name");
            String currentLN=(String)informations.get(i).get("last name");
            Account account=new Account(currentFN,currentLN);
            ArrayList<AvailabilityTime> availabilityTimes=findAvailabilitiesBooked(account);
            informations.get(i).put("times", availabilityTimes);
        }
        cursor.close();
        db.close();
        return informations;
    }

    public ArrayList<HashMap<String, Object>> searchByTime(AvailabilityTime availabilityTime){
        ArrayList<HashMap<String, Object>> informations=new ArrayList<HashMap<String, Object>>();
        SQLiteDatabase db=this.getReadableDatabase();
        String query="Select * FROM "+TABLE_AVAILABILITY_BOOK
                +" WHERE " +COLUMN_DATE+"=\""+availabilityTime.getDate()+"\""
                +" AND "+COLUMN_STARTHOUR+"<="+availabilityTime.getStartHour()
                +" AND "+COLUMN_ENDHOUR+">="+availabilityTime.getEndHour();
        Cursor cursor=db.rawQuery(query,null);
        String firstName="";
        String lastName="";
        ArrayList<AvailabilityTime> availabilityTimes=new ArrayList<AvailabilityTime>();
        HashMap<String, Object> map=new HashMap<String, Object>();
        while (cursor.moveToNext()){
            String currentFN=cursor.getString(1);
            String currentLN=cursor.getString(2);
            if((firstName.equals("") && lastName.equals("")) || !(currentFN.equals(firstName) && currentLN.equals(lastName))){
                if(!(firstName.equals("") && lastName.equals(""))){
                    informations.add(map);
                }
                map=new HashMap<String, Object>();
                map.put("first name", currentFN);
                map.put("last name", currentLN);
                availabilityTimes=new ArrayList<AvailabilityTime>();
            }
            firstName=currentFN;
            lastName=currentLN;
            AvailabilityTime time=new AvailabilityTime();
            time.setDate(cursor.getString(3));
            time.setStartHour(Integer.parseInt(cursor.getString(4)));
            time.setStartMin(Integer.parseInt(cursor.getString(5)));
            time.setEndHour(Integer.parseInt(cursor.getString(6)));
            time.setEndMin(Integer.parseInt(cursor.getString(7)));
            time.setBooked(cursor.getString(8));
            availabilityTimes.add(time);
            map.put("times", availabilityTimes);
            if(cursor.isLast()){
                informations.add(map);
            }
        }
        for(int i=0;i<informations.size();i++){
            String currentFN=(String)informations.get(i).get("first name");
            String currentLN=(String)informations.get(i).get("last name");
            Account account=new Account(currentFN,currentLN);
            account.setRole("SERVICE PROVIDER");
            ArrayList<Service> services=findServices(account);
            informations.get(i).put("services", services);
        }
        cursor.close();
        db.close();
        return informations;
    }

    public ArrayList<HashMap<String, Object>> searchByRate(int minimum, int maximum){
        ArrayList<HashMap<String, Object>> informations=new ArrayList<HashMap<String, Object>>();
        SQLiteDatabase db=this.getReadableDatabase();
        String query="Select * FROM "+TABLE_SERVICES_SV
                +" WHERE "+COLUMN_RATING+" is null"
                +" OR ("+COLUMN_RATING+">="+minimum +" AND "+COLUMN_RATING+"<="+maximum+")";
        Cursor cursor=db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String, Object> map=new HashMap<String, Object>();
            map.put("first name", cursor.getString(1));
            map.put("last name", cursor.getString(2));
            map.put("service name", cursor.getString(3));
            map.put("service rate", cursor.getString(4));
            map.put("rating", cursor.getString(5));
            informations.add(map);
        }
        for(int i=0;i<informations.size();i++){
            String currentFN=(String)informations.get(i).get("first name");
            String currentLN=(String)informations.get(i).get("last name");
            Account account=new Account(currentFN,currentLN);
            ArrayList<AvailabilityTime> availabilityTimes=findAvailabilitiesBooked(account);
            informations.get(i).put("times", availabilityTimes);
        }
        cursor.close();
        db.close();
        return informations;
    }

    public void addToHO(Account account, Account spAccount, Service service, AvailabilityTime availabilityTime){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COLUMN_FIRSTNAME, account.getFirstName());
        contentValues.put(COLUMN_LASTNAME, account.getLastName());
        contentValues.put(COLUMN_SPFIRSTNAME, spAccount.getFirstName());
        contentValues.put(COLUMN_SPLASTNAME, spAccount.getLastName());
        contentValues.put(COLUMN_SERVICENAME, service.getName());
        contentValues.put(COLUMN_SERVICERATE, service.getRate());
        contentValues.put(COLUMN_DATE, availabilityTime.getDate());
        contentValues.put(COLUMN_STARTHOUR, availabilityTime.getStartHour());
        contentValues.put(COLUMN_STARTMIN, availabilityTime.getStartMin());
        contentValues.put(COLUMN_ENDHOUR, availabilityTime.getEndHour());
        contentValues.put(COLUMN_ENDMIN, availabilityTime.getEndMin());
        db.insert(TABLE_RATE, null, contentValues);
        db.close();
    }

    public ArrayList<HashMap<String, Object>> findHO(){
        SQLiteDatabase db=this.getReadableDatabase();
        String query="Select * FROM "+TABLE_RATE;
        Cursor cursor=db.rawQuery(query,null);
        ArrayList<HashMap<String, Object>> informations=new ArrayList<HashMap<String, Object>>();
        while (cursor.moveToNext()){
            HashMap<String, Object> map=new HashMap<String, Object>();
            Account account=new Account(cursor.getString(1), cursor.getString(2));
            map.put("account", account);
            Service service=new Service(cursor.getString(3), Integer.parseInt(cursor.getString(4)));
            map.put("service", service);
            AvailabilityTime availabilityTime=new AvailabilityTime();
            availabilityTime.setDate(cursor.getString(5));
            availabilityTime.setStartHour(Integer.parseInt(cursor.getString(6)));
            availabilityTime.setStartMin(Integer.parseInt(cursor.getString(7)));
            availabilityTime.setEndHour(Integer.parseInt(cursor.getString(8)));
            availabilityTime.setEndMin(Integer.parseInt(cursor.getString(9)));
            map.put("time", availabilityTime);
            Account account1=new Account(cursor.getString(11), cursor.getString(12));
            map.put("spaccount", account1);
            informations.add(map);
        }
        cursor.close();
        db.close();
        return informations;
    }

    public boolean removeFromHO(Account account, Service service, AvailabilityTime availabilityTime){
        boolean result=false;
        String query="Select * FROM "+TABLE_RATE+" WHERE "+COLUMN_FIRSTNAME+"=\""+account.getFirstName()+"\""
                +" AND "+COLUMN_LASTNAME+"=\""+account.getLastName()+"\""
                +" AND "+COLUMN_SERVICENAME+"=\""+service.getName()+"\""
                +" AND "+COLUMN_DATE+"=\""+availabilityTime.getDate()+"\""
                +" AND "+COLUMN_STARTHOUR+"=\""+availabilityTime.getStartHour()+"\""
                +" AND "+COLUMN_STARTMIN+"=\""+availabilityTime.getStartMin()+"\""
                +" AND "+COLUMN_ENDHOUR+"=\""+availabilityTime.getEndHour()+"\""
                +" AND "+COLUMN_ENDMIN+"=\""+availabilityTime.getEndMin()+"\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            String idStr = cursor.getString(0);
            db.delete(TABLE_RATE, COLUMN_ID + " = " + idStr, null);
            result = true;
        }
        cursor.close();
        db.close();
        return result;
    }
}
