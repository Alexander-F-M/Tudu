package com.example.alexander.tudu.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.alexander.tudu.FrontpageFragment;
import com.example.alexander.tudu.R;
import com.example.alexander.tudu.logic.Task;

import java.util.ArrayList;

/**
 * Created by Alexander on 11-04-2016.
 */
public class TaskAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Task> taskList;

    public TaskAdapter(Context ctx, ArrayList<Task> task) {
        context = ctx;
        taskList = task;
    }


    @Override
    public int getCount() {
        return taskList.size();
    }

    @Override
    public Object getItem(int position) {
        return taskList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        TextView taskTitle;
        CheckBox checkbox;

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View task;

        if (convertView == null) {
            task = inflater.inflate(R.layout.list_item_tasks, null);
        } else {
            task = convertView;
        }

        taskTitle = (TextView) task.findViewById(R.id.task_title);
        taskTitle.setText(taskList.get(position).getName());

        checkbox = (CheckBox) task.findViewById(R.id.task_checkbox);
        checkbox.setChecked(taskList.get(position).getDone());

        checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = taskList.get(position).getDone();
                if(isChecked) {
                    taskList.get(position).setDone(false);
                } else if(!isChecked) {
                    taskList.get(position).setDone(true);
                }
            }
            });

        /*
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    taskList.get(position).setDone(true);
                } else if(!isChecked) {
                    taskList.get(position).setDone(false);
                }
                // update your model (or other business logic) based on isChecked
            }
        });
        */
        return task;
    }
}
