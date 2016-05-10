package com.example.alexander.tudu;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Alexander on 09-05-2016.
 */
public class CreateTaskFragment extends DialogFragment implements View.OnClickListener {

    Button dismiss_task, accept_task;
    EditText editAddTask;

    public interface CreateTaskListener {
        void onFinishEditDialog(String inputText);
    }

    public static CreateTaskFragment newInstance(){
        CreateTaskFragment fragment = new CreateTaskFragment();
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.create_task_fragment, container);
        getDialog().setTitle("Tilføj Punkt");

        editAddTask = (EditText) root.findViewById(R.id.editAddTask);
        dismiss_task = (Button) root.findViewById(R.id.dismiss_task);
        accept_task = (Button) root.findViewById(R.id.accept_task);

        accept_task.setOnClickListener(this);
        dismiss_task.setOnClickListener(this);



        return root;
    }

    @Override
    public void onClick(View v) {
        if(v == accept_task) {
            sendBackResult();
        }
        if (v == dismiss_task) {
            getDialog().cancel();
        }
    }

    public void sendBackResult(){
        CreateTaskListener listener = (CreateTaskListener) getTargetFragment();
        listener.onFinishEditDialog(editAddTask.getText().toString());
        dismiss();
    }
}
