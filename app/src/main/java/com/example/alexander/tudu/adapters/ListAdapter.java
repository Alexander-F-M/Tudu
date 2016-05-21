package com.example.alexander.tudu.adapters;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alexander.tudu.FrontpageFragment;
import com.example.alexander.tudu.MainActivity;
import com.example.alexander.tudu.R;
import com.example.alexander.tudu.TaskActivity;
import com.example.alexander.tudu.VPFragment;
import com.example.alexander.tudu.logic.Lists;
import com.example.alexander.tudu.logic.Logic;
import com.example.alexander.tudu.logic.User;

import java.util.ArrayList;

/**
 * Created by Alexander on 03-04-2016.
 */
public class ListAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<Lists> todoLists;
    private User user;
    private VPFragment.ListFragment frag;

    public ListAdapter(Context ctx, ArrayList<Lists> list, VPFragment.ListFragment listf) {
        context = ctx;
        todoLists = list;
        frag = listf;
        user = Logic.getLogic().getUser();
    }


    @Override
    public int getCount() {
        return todoLists.size();
    }

    @Override
    public Object getItem(int position) {
        return todoLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        TextView title, task_nr;
        ImageButton list_delete, list_edit;

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View list;

        if (convertView == null) {
            list = inflater.inflate(R.layout.list_item_lists, null);


        } else {
            list = convertView;
        }

        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TaskActivity.class);
                if(intent != null) {
                    user.setLists(todoLists.get(position));
                    context.startActivity(intent);
                }
            }
        });

        title = (TextView) list.findViewById(R.id.list_title);
        title.setText(todoLists.get(position).getName());

        list_delete = (ImageButton) list.findViewById(R.id.list_delete);
        list_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frag.deleteDialog(todoLists.get(position).getName(), position);
            }
        });

        list_edit = (ImageButton) list.findViewById(R.id.list_edit);
        list_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                frag.editDialog(todoLists.get(position).getName(), position);
            }
        });

        task_nr = (TextView) list.findViewById(R.id.list_task_nr);
        int i = 0;

        for(int s = 0; s < todoLists.get(position).tasks.size(); s++){
            if(!todoLists.get(position).tasks.get(s).getDone()) {
                i++;
            }
        }

        if(i == 1) {
            task_nr.setText("1 uløst punkt");
        } else {
            task_nr.setText(i + " uløste punkter");
        }

        return list;
    }


}
