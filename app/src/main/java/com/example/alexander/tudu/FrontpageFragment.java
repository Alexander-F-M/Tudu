package com.example.alexander.tudu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Alexander on 22-03-2016.
 */
public class FrontpageFragment extends Fragment implements View.OnClickListener {

    public FrontpageFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.frontpage_frag, container, false);
        //TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));

        root.findViewById(R.id.button).setOnClickListener(this);

        return root;

    }

    @Override
    public void onClick(View v) {

        getFragmentManager().popBackStack();

        /*
        getFragmentManager().beginTransaction()
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .replace(R.id.mainactivity_content, new VPFragment())
                .addToBackStack(null)
                .commit();
                */
    }
}
