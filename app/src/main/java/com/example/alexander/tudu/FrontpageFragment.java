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
import com.example.alexander.tudu.logic.Task;

import java.util.ArrayList;

/**
 * Created by Alexander on 22-03-2016.
 */
public class FrontpageFragment extends Fragment implements CreateTaskFragment.CreateTaskListener {

    ListView tasks;
    TextView listname;
    Lists list;
    TaskAdapter adapter;

    public FrontpageFragment(){

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

        /*
        Bundle bundle = this.getArguments();
        Lists list = (Lists) bundle.getParcelable("list");
        */
        list = ((TaskActivity) getActivity()).getList();

        ArrayList<Task> taskList = list.tasks;
        adapter = new TaskAdapter(getActivity(), taskList);

       // tasks.addFooterView(footer);

        footerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                    showDialog();

                /*
                Task task = new Task("Nyt Punkt");
                list.addTask(task);
                adapter.notifyDataSetChanged();
                Toast.makeText(getActivity(), "hej", Toast.LENGTH_LONG).show();
                */
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

    public void onFinishEditDialog(String inputText) {
        //Toast.makeText(getActivity(), "Hi, " + inputText, Toast.LENGTH_SHORT).show();
        Task task = new Task(inputText);
        list.addTask(task);
        adapter.notifyDataSetChanged();

    }

    /*public void addItems(View v) {
        taskList.add(new Task("GER"));
    }
    */


            //popBackStack();

        /*
        getFragmentManager().beginTransaction()
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .replace(R.id.mainactivity_content, new VPFragment())
                .addToBackStack(null)
                .commit();
                */

}
