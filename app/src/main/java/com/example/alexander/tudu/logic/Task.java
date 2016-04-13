package com.example.alexander.tudu.logic;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alexander on 31-03-2016.
 */

public class Task implements Parcelable {
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

    protected Task(Parcel in) {
        name = in.readString();
        done = in.readByte() != 0x00;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeByte((byte) (done ? 0x01 : 0x00));
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Task> CREATOR = new Parcelable.Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };
}