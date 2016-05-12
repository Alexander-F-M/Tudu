package com.example.alexander.tudu.logic;

import java.util.ArrayList;

/**
 * Created by Alexander on 31-03-2016.
 */
public class User {
    private String name;
    public ArrayList<Lists> lists;
    public Lists selected;
    private static User ourInstance = new User();

    public User(){
    }

    public static User getInstance(){
        return ourInstance;
    }

    public User(String name) {

        this.name = name;
    }

    public void setName(String newName) {
       this.name = newName;
    }

    public String getName() {
        return name;
    }

    public void setLists(Lists list){
        this.selected = list;
    }

    public Lists getLists(){
        return selected;
    }

    public void addList(Lists newList) {
        lists.add(newList);
    }

    public ArrayList<Lists> getListsAsArray() {
        return lists;
    }



}
