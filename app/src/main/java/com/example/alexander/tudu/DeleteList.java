package com.example.alexander.tudu;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Alexander on 18-05-2016.
 */
public class DeleteList extends DialogFragment implements View.OnClickListener {

    Button dismiss_delete_list, accept_delete_list;
    private static String listName;
    private static int pos;

    public interface DeleteListListener {
        void onFinishDeleteDialog(String name, int position);
    }

    public static DeleteList newInstance(String name, int position){
        DeleteList fragment = new DeleteList();
        listName = name;
        pos = position;
        fragment.setStyle(DialogFragment.STYLE_NORMAL,R.style.CustomDialog);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.delete_fragment, container);
        getDialog().setTitle("Vil du slette '" + listName + "'?");

        dismiss_delete_list = (Button) root.findViewById(R.id.dismiss_delete_task);
        accept_delete_list = (Button) root.findViewById(R.id.accept_delete_task);

        dismiss_delete_list.setOnClickListener(this);
        accept_delete_list.setOnClickListener(this);


        return root;
    }

    @Override
    public void onClick(View v) {
        if(v == accept_delete_list) {
            sendBackResult();
        }
        if (v == dismiss_delete_list) {
            getDialog().cancel();
        }
    }

    public void sendBackResult(){
        DeleteListListener listener = (DeleteListListener) getTargetFragment();
        listener.onFinishDeleteDialog(listName, pos);
        dismiss();
    }
}
