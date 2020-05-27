package com.example.bjzha.project;

import java.io.Serializable;

public class Account implements Serializable {
    private String firstName;
    private String lastName;
    private String password;
    private String role;

    public Account(String firstName, String lastName, String password, String role){
        this.firstName=firstName.toUpperCase();
        this.lastName=lastName.toUpperCase();
        this.password=password;
        this.role=role.toUpperCase();
    }

    public Account(){}

    public Account(String firstName, String lastName){
        this.firstName=firstName.toUpperCase();
        this.lastName=lastName.toUpperCase();
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getPassword(){
        return password;
    }

    public String getRole(){
        return role;
    }

    public void setFirstName(String firstName){
        this.firstName=firstName.toUpperCase();
    }

    public void setLastName(String lastName){
        this.lastName=lastName.toUpperCase();
    }

    public void setPassword(String password){
        this.password=password;
    }

    public void setRole(String role){
        this.role=role.toUpperCase();
    }
}
