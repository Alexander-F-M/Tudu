package com.example.alexander.tudu.logic;

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
        teddy.lists = new Lists[] {new Lists("Dagens To-Do"), new Lists("Træning")};

        Lists dagensToDo = teddy.lists[0];
        dagensToDo.tasks = new Task[] {
                new Task("Ryd op"),
                new Task("Gør rent"),
                new Task("Køb mælk")
        };

        dagensToDo.tasks[0].setDone(true);

        Lists træning = teddy.lists[1];
        træning.tasks = new Task[] {
                new Task("Biceps"),
                new Task("Triceps")
        };
    }


    public Lists[] getListsAsArray() {
        return teddy.lists;
    }

    public Task[] getTasksAsArray() {
        Lists[] list = getListsAsArray();
        return list[0].tasks;
    }
}
