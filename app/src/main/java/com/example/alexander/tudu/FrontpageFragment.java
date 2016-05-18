package com.example.alexander.tudu;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alexander.tudu.adapters.ListAdapter;
import com.example.alexander.tudu.adapters.TaskAdapter;
import com.example.alexander.tudu.logic.Lists;
import com.example.alexander.tudu.logic.Logic;
import com.example.alexander.tudu.logic.Task;
import com.example.alexander.tudu.logic.User;

import java.util.ArrayList;

/**
 * Created by Alexander on 22-03-2016.
 */
public class FrontpageFragment extends Fragment implements CreateTaskFragment.CreateTaskListener,
        DeleteTask.DeleteTaskListener{

    ListView tasks;
    TextView listname;
    Lists list;
    TaskAdapter adapter;
    private User user;

    public FrontpageFragment(){
        user = Logic.getLogic().getUser();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.frontpage_frag, container, false);
        //TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));

        tasks = (ListView) root.findViewById(R.id.tasks);
        listname = (TextView) root.findViewById(R.id.list_task_title);

        //View footer = inflater.inflate(R.layout.footer_add, null, false);
        Button footerButton = (Button) root.findViewById(R.id.addTask);

        list = user.getLists();

        ArrayList<Task> taskList = list.tasks;
        adapter = new TaskAdapter(getActivity(), taskList, this);

       // tasks.addFooterView(footer);

        footerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    showDialog();
            }
        });


        tasks.setAdapter(adapter);

        listname.setText(list.getName());

        return root;

    }

    public void showDialog(){
        FragmentManager fm = getActivity().getSupportFragmentManager();
        CreateTaskFragment fragment = CreateTaskFragment.newInstance();
        fragment.setTargetFragment(this, 300);
        fragment.show(fm, "Tilføj punkt");
    }

    @Override
    public void onFinishEditDialog(String inputText) {
        //Toast.makeText(getActivity(), "Hi, " + inputText, Toast.LENGTH_SHORT).show();
        Task task = new Task(inputText);
        list.addTask(task);
        user.save();
        adapter.notifyDataSetChanged();

    }

    public void deleteDialog(String name, int position){
        FragmentManager fm =  getActivity().getSupportFragmentManager();
        DeleteTask fragment = DeleteTask.newInstance(name, position);
        fragment.setTargetFragment(this,300);
        fragment.show(fm,"Slet punkt");
    }


    @Override
    public void onFinishDeleteDialog(String name, int position) {
        list.deleteTask(position);
        adapter.notifyDataSetChanged();
        user.save();
        Toast.makeText(getActivity(), "Punktet '" + name + "' blev slettet", Toast.LENGTH_SHORT).show();
    }
}
