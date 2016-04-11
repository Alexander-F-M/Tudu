package com.example.alexander.tudu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.alexander.tudu.adapters.ListAdapter;
import com.example.alexander.tudu.adapters.TaskAdapter;
import com.example.alexander.tudu.logic.Lists;
import com.example.alexander.tudu.logic.Task;

/**
 * Created by Alexander on 22-03-2016.
 */
public class FrontpageFragment extends Fragment {

    ListView tasks;
    TextView listname;

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

        Bundle bundle = this.getArguments();
        Lists list = (Lists) bundle.getParcelable("list");

        final Task[] taskList = list.tasks;
        final TaskAdapter adapter = new TaskAdapter(getActivity(), taskList);

        tasks.setAdapter(adapter);

        listname.setText(list.getName());

        return root;

    }


            //popBackStack();

        /*
        getFragmentManager().beginTransaction()
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .replace(R.id.mainactivity_content, new VPFragment())
                .addToBackStack(null)
                .commit();
                */

}
