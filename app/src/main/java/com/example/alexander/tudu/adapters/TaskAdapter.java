package com.example.alexander.tudu.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
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
    FrontpageFragment frontpage;

    public TaskAdapter(Context ctx, ArrayList<Task> task, FrontpageFragment front) {
        context = ctx;
        taskList = task;
        frontpage = front;
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
        final TextView taskTitle;
        CheckBox checkbox;
        ImageButton task_delete;

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View task;

        if (convertView == null) {
            task = inflater.inflate(R.layout.list_item_tasks, null);
        } else {
            task = convertView;
        }

        taskTitle = (TextView) task.findViewById(R.id.task_title);
        taskTitle.setText(taskList.get(position).getName());

        task_delete = (ImageButton) task.findViewById(R.id.task_delete);
        task_delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                frontpage.deleteDialog(taskList.get(position).getName(), position);
            }
        });

        checkbox = (CheckBox) task.findViewById(R.id.task_checkbox);
        checkbox.setChecked(taskList.get(position).getDone());

        checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = taskList.get(position).getDone();
                if (isChecked) {
                    taskList.get(position).setDone(false);
                } else if (!isChecked) {
                    taskList.get(position).setDone(true);
                }
            }
        });

        return task;
    }
}
