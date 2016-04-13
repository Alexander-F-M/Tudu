package com.example.alexander.tudu.logic;

import java.util.ArrayList;

/**
 * Created by Alexander on 31-03-2016.
 */
public class User {
    private String name;
    public ArrayList<Lists> lists;

    public User(String name) {

        this.name = name;
    }

    public void setName(String newName) {
       // this.name = newName;
    }

    public String getName() {
        return name;
    }



}
