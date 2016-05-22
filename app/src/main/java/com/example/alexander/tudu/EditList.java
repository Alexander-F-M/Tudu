package com.example.alexander.tudu;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Alexander on 21-05-2016.
 */
public class EditList extends DialogFragment implements View.OnClickListener{

    Button dismiss_list, accept_list;
    EditText editList;
    private static String listName;
    private static int pos;

    public interface EditListListener{
        void onFinishedEditListDialog(String name, int position);
    }

    public static EditList newInstance(String newName, int position){
        EditList fragment = new EditList();
        listName = newName;
        pos = position;
        fragment.setStyle(DialogFragment.STYLE_NORMAL,R.style.CustomDialog);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.create_task_fragment, container);
        getDialog().setTitle("Redigér listenavn");

        editList = (EditText) root.findViewById(R.id.editAddTask);
        dismiss_list = (Button)  root.findViewById(R.id.dismiss_task);
        accept_list = (Button) root.findViewById(R.id.accept_task);

        dismiss_list.setOnClickListener(this);
        accept_list.setOnClickListener(this);

        accept_list.setText("BEKRÆFT");
        editList.setText(listName);

        if(editList.requestFocus()) {
            InputMethodManager imgr = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imgr.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
            //getFragmentManager().get .getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }

        editList.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void afterTextChanged(Editable s) {
                /*
                 * The loop is in reverse for a purpose,
                 * each replace or delete call on the Editable will cause
                 * the afterTextChanged method to be called again.
                 * Hence the return statement after the first removal.
                 * http://developer.android.com/reference/android/text/TextWatcher.html#afterTextChanged(android.text.Editable)
                 */
                for (int i = s.length() - 1; i > 0; i--) {
                    if (s.charAt(i) == '\n') {
                        s.delete(i, i + 1);
                        return;
                    }
                }
            }
        });

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
        EditListListener listener = (EditListListener) getTargetFragment();
        listener.onFinishedEditListDialog(editList.getText().toString(), pos);
        dismiss();
    }
}
