package com.example.alexander.tudu.logic;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by Alexander on 18-05-2016.
 */
public class Storage {
    private static Storage instance = new Storage();
    private Context context;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;


    private Storage() {}

    public static Storage getInstance() {
        return instance;
    }

    public Storage init(Context context) {
        this.context = context;
        pref = PreferenceManager.getDefaultSharedPreferences(context);
        return this;
    }

    public User loadUser() {
        User user;
        pref = PreferenceManager
                .getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = pref.getString("User", "");
        user = gson.fromJson(json, User.class);
        if(user == null) {
            user = User.getInstance();
            user.lists = new ArrayList<>();
        }
        return user;
    }

    public void saveUser(User user) {
               pref = PreferenceManager
                .getDefaultSharedPreferences(context);
        editor = pref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        editor.putString("User", json);
        editor.commit();
    }
}
