package com.example.alexander.tudu;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.alexander.tudu.logic.Lists;
import com.example.alexander.tudu.logic.Logic;
import com.example.alexander.tudu.logic.User;

/**
 * Created by Alexander on 13-04-2016.
 */
public class TaskActivity extends AppCompatActivity {
    private User user;
    public TaskActivity(){
        user = Logic.getLogic().getUser();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.taskactivity);
        Lists list = user.getLists();
        setTitle(list.getName());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
           getSupportFragmentManager().beginTransaction().add(R.id.taskactivity_content, new FrontpageFragment()).commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
