package com.example.alexander.tudu.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

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
    public View getView(int position, View convertView, ViewGroup parent) {
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
        if(taskList.get(position).getDone()){
            checkbox.setChecked(true);
        }

        return task;
    }
}
