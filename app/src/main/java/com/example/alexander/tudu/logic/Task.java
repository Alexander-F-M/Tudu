package com.example.alexander.tudu.logic;

/**
 * Created by Alexander on 31-03-2016.
 */
public class Task {
    private String name;
    private boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public String getName() {
        return name;
    }

    public void setDone(boolean isdone){
        done = isdone;
    }

    public boolean getDone(){
        return done;
    }
}
