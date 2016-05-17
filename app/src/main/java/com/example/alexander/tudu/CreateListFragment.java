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
 * Created by Alexander on 12-05-2016.
 */
public class CreateListFragment extends DialogFragment implements View.OnClickListener {

    Button dismiss_list, accept_list;
    EditText editAddList;
    View view;

    public interface CreateListListener {
        void onFinishEditDialog(String inputText);
    }

    public static CreateListFragment newInstance(){
        CreateListFragment fragment = new CreateListFragment();
        fragment.setStyle(DialogFragment.STYLE_NORMAL,R.style.CustomDialog);
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

        if(editAddList.requestFocus()) {
            InputMethodManager imgr = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imgr.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
            //getFragmentManager().get .getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }

        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );

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
