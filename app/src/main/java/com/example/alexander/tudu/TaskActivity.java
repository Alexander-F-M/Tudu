package com.example.alexander.tudu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.alexander.tudu.logic.Lists;

/**
 * Created by Alexander on 13-04-2016.
 */
public class TaskActivity extends AppCompatActivity {

    public Lists list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("TaskActivity startet");
        setContentView(R.layout.taskactivity);

        Bundle bundle = getIntent().getExtras();
       list = (Lists) bundle.getParcelable("list");
        System.out.println(list.getName());
        System.out.println(list.tasks.get(0).getName());

        if (savedInstanceState == null) {
           getSupportFragmentManager().beginTransaction().add(R.id.taskactivity_content, new FrontpageFragment()).commit();
        }
    }

    public Lists getList() {
        return list;
    }
}
