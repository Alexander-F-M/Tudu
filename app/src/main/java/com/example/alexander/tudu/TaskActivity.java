package com.example.alexander.tudu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Alexander on 13-04-2016.
 */
public class TaskActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.taskactivity);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.taskactivity_content, new FrontpageFragment()).commit();
        }

    }
}
