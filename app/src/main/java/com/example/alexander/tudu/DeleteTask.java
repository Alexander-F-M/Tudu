package com.example.alexander.tudu;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Alexander on 17-05-2016.
 */
public class DeleteTask extends DialogFragment implements View.OnClickListener {

    Button dismiss_delete_task, accept_delete_task;
    private static String taskName;
    private static int pos;

    public interface DeleteTaskListener {
        void onFinishDeleteDialog(String name, int position);
    }

    public static DeleteTask newInstance(String name, int position){
        DeleteTask fragment = new DeleteTask();
        taskName = name;
        pos = position;
        fragment.setStyle(DialogFragment.STYLE_NORMAL,R.style.CustomDialog);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.delete_fragment, container);
        getDialog().setTitle("Vil du slette '" + taskName + "'?");

        dismiss_delete_task = (Button) root.findViewById(R.id.dismiss_delete_task);
        accept_delete_task = (Button) root.findViewById(R.id.accept_delete_task);

        dismiss_delete_task.setOnClickListener(this);
        accept_delete_task.setOnClickListener(this);


        return root;
    }

    @Override
    public void onClick(View v) {
        if(v == accept_delete_task) {
            sendBackResult();
        }
        if (v == dismiss_delete_task) {
            getDialog().cancel();
        }
    }

    public void sendBackResult(){
        DeleteTaskListener listener = (DeleteTaskListener) getTargetFragment();
        listener.onFinishDeleteDialog(taskName, pos);
        dismiss();
    }
}
