package com.example.alexander.tudu.logic;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alexander on 31-03-2016.
 */
public class Lists implements Parcelable {
    private String name;
    public Task[] tasks;

    public Lists(String name) {
        this.name = name;
    }

    protected Lists(Parcel in) {
        name = in.readString();
    }

    public static final Creator<Lists> CREATOR = new Creator<Lists>() {
        @Override
        public Lists createFromParcel(Parcel in) {
            return new Lists(in);
        }

        @Override
        public Lists[] newArray(int size) {
            return new Lists[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        //this.name = newName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }
}
