package com.example.alexander.tudu.logic;

import android.app.Application;

import java.util.ArrayList;

/**
 * Created by Alexander on 31-03-2016.
 */
public class Logic extends Application {
    public static Logic instance;
    private User[] users;
    private User teddy;
    private Lists selected;

    public Logic(){
        teddy = User.getInstance();
    }

    public void TestData() {

        users = new User[]{new User("Teddy Bridgewater")};

        teddy.setName("Teddy Bridgewater");
        teddy.lists = new ArrayList<>();
        teddy.lists.add(new Lists("Dagens To-Do"));
        teddy.lists.add(new Lists("Træning"));

        selected = teddy.lists.get(0);

                Lists dagensToDo = teddy.lists.get(0);
        dagensToDo.tasks = new ArrayList<>();
        dagensToDo.tasks.add(new Task("Ryd op"));
        dagensToDo.tasks.add(new Task("Gør rent"));
        dagensToDo.tasks.add(new Task("Køb mælk"));

        dagensToDo.tasks.get(0).setDone(true);

        Lists træning = teddy.lists.get(1);
        træning.tasks = new ArrayList<>();
        //træning.tasks.add(new Task("Biceps"));
        //træning.tasks.add(new Task("Triceps"));
    }


    public ArrayList<Lists> getListsAsArray() {
        return teddy.lists;
    }

    public User getUser() {
        return teddy;
    }

    public static Logic getLogic(){
        return instance;
    }

}
