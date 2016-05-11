package com.example.alexander.tudu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.alexander.tudu.logic.Lists;

/**
 * Created by Alexander on 13-04-2016.
 */
public class TaskActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("TaskActivity startet");
        setContentView(R.layout.taskactivity);

        if (savedInstanceState == null) {
           getSupportFragmentManager().beginTransaction().add(R.id.taskactivity_content, new FrontpageFragment()).commit();
        }
    }
}
