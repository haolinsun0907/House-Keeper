package com.example.bjzha.project.serviceProvider;

import java.io.Serializable;

public class AvailabilityTime implements Serializable {
    private String date;
    private int startHour, startMin, endHour, endMin;
    private String booked;

    public AvailabilityTime(String date, int startHour, int startMin, int endHour, int endMin){
        this.date=date.toUpperCase();
        this.startHour=startHour;
        this.startMin=startMin;
        this.endHour=endHour;
        this.endMin=endMin;
        booked="false".toUpperCase();
    }

    public AvailabilityTime(){
        booked="false".toUpperCase();
    }

    public String getDate(){
        return date;
    }

    public int getStartHour(){
        return startHour;
    }

    public int getStartMin(){
        return startMin;
    }

    public int getEndHour(){
        return endHour;
    }

    public int getEndMin(){
        return endMin;
    }

    public String getBooked(){
        return booked;
    }

    public void setDate(String date){
        this.date=date.toUpperCase();
    }

    public void setStartHour(int startHour){
        this.startHour=startHour;
    }

    public void setStartMin(int startMin){
        this.startMin=startMin;
    }

    public void setEndHour(int endHour){
        this.endHour=endHour;
    }

    public void setEndMin(int endMin){
        this.endMin=endMin;
    }

    public void setBooked(String booked){
        this.booked=booked.toUpperCase();
    }

    @Override
    public String toString(){
        String startMinString="";
        String endMinString="";
        if(startMin<10){
            startMinString="0"+startMin;
        }
        else{
            startMinString=Integer.toString(startMin);
        }
        if(endMin<10){
            endMinString="0"+endMin;
        }
        else{
            endMinString=Integer.toString(endMin);
        }
        return date+" "+startHour+"h"+startMinString+"-"+endHour+"h"+endMinString;
    }

    public boolean equals(AvailabilityTime other) {
        return date==other.getDate() && startHour==other.getStartHour() && startMin==other.getStartMin() && endHour==other.getEndHour() && endMin==other.getEndMin();
    }
}
