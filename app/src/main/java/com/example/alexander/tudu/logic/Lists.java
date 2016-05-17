package com.example.alexander.tudu.logic;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Alexander on 31-03-2016.
 */
public class Lists implements Parcelable {
    private String name;
    public ArrayList<Task> tasks;

    public Lists(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        //this.name = newName;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int position){
        tasks.remove(position);
    }

    protected Lists(Parcel in) {
        name = in.readString();
        if (in.readByte() == 0x01) {
            tasks = new ArrayList<Task>();
            in.readList(tasks, Task.class.getClassLoader());
        } else {
            tasks = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        if (tasks == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(tasks);
        }
    }

@SuppressWarnings("unused")
public static final Parcelable.Creator<Lists> CREATOR = new Parcelable.Creator<Lists>() {
    @Override
    public Lists createFromParcel(Parcel in) {
        return new Lists(in);
    }

    @Override
    public Lists[] newArray(int size) {
        return new Lists[size];
    }
};
}
