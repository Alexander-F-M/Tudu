package com.example.alexander.tudu.logic;

import java.util.ArrayList;

/**
 * Created by Alexander on 31-03-2016.
 */
public class Logic {
    public static Logic instance;
    private User[] users;
    User teddy;

    public void TestData() {

        users = new User[]{new User("Teddy Bridgewater"), new User("Trae Waynes")};

        teddy = users[0];
        teddy.lists = new ArrayList<>();
        teddy.lists.add(new Lists("Dagens To-Do"));
        teddy.lists.add(new Lists("Træning"));

        Lists dagensToDo = teddy.lists.get(0);
        dagensToDo.tasks = new ArrayList<>();
        dagensToDo.tasks.add(new Task("Ryd op"));
        dagensToDo.tasks.add(new Task("Gør rent"));
        dagensToDo.tasks.add(new Task("Køb mælk"));

        dagensToDo.tasks.get(0).setDone(true);

        Lists træning = teddy.lists.get(1);
        træning.tasks = new ArrayList<>();
        træning.tasks.add(new Task("Biceps"));
        træning.tasks.add(new Task("Triceps"));
    }


    public ArrayList<Lists> getListsAsArray() {
        return teddy.lists;
    }

}
