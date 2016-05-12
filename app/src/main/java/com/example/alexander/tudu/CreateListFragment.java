package com.example.alexander.tudu;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Alexander on 12-05-2016.
 */
public class CreateListFragment extends DialogFragment implements View.OnClickListener {

    Button dismiss_list, accept_list;
    EditText editAddList;

    public interface CreateListListener {
        void onFinishEditDialog(String inputText);
    }

    public static CreateListFragment newInstance(){
        CreateListFragment fragment = new CreateListFragment();
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.create_task_fragment, container);
        getDialog().setTitle("Tilføj Liste");

        editAddList = (EditText) root.findViewById(R.id.editAddTask);
        dismiss_list = (Button) root.findViewById(R.id.dismiss_task);
        accept_list = (Button) root.findViewById(R.id.accept_task);

        accept_list.setOnClickListener(this);
        dismiss_list.setOnClickListener(this);



        return root;
    }

    @Override
    public void onClick(View v) {
        if(v == accept_list) {
            sendBackResult();
        }
        if (v == dismiss_list) {
            getDialog().cancel();
        }
    }

    public void sendBackResult(){
        CreateListListener listener = (CreateListListener) getTargetFragment();
        listener.onFinishEditDialog(editAddList.getText().toString());
        dismiss();
    }
}
