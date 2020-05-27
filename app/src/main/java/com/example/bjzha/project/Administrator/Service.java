package com.example.bjzha.project.Administrator;

import java.io.Serializable;

public class Service implements Serializable {
    private String name;
    private int rate;
    private int rating;
    public Service(String name, int rate){
        this.name=name.toUpperCase();
        this.rate=rate;
    }

    public Service(){}

    public String getName(){
        return name;
    }

    public int getRate(){
        return rate;
    }

    public int getRating(){
        return rating;
    }

    public void setName(String name){
        this.name=name.toUpperCase();
    }

    public void setRate(int rate){
        this.rate=rate;
    }

    public String toString(){
        String s=name+", "+rate+"$ hourly rate";
        return s;
    }

    public void setRating(int rating){
        this.rating=rating;
    }
}
