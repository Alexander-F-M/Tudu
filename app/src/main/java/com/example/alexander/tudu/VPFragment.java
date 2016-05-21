package com.example.alexander.tudu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alexander.tudu.adapters.ListAdapter;
import com.example.alexander.tudu.logic.Lists;
import com.example.alexander.tudu.logic.Logic;
import com.example.alexander.tudu.logic.User;

import java.util.ArrayList;

/**
 * Created by Alexander on 22-03-2016.
 */
public class VPFragment extends Fragment {
    private SectionsPagerAdapter mSectionsPagerAdapter;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.vp_fragment, container, false);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) root.findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) root.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        return root;

    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0: return ListFragment.newInstance(position);
                default: return PlaceholderFragment.newInstance(position);
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Tudu";
                case 1:
                    return "Kalender";
                case 2:
                    return "Kontakter";
            }
            return null;
        }
    }

    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.vp_frag2, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label4);
            //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    public static class ListFragment extends Fragment implements CreateListFragment.CreateListListener,
            DeleteList.DeleteListListener, EditList.EditListListener{

        ListView lists;
        Button addList;
        private User user;
        ListAdapter adapter;


        public ListFragment() {
        }

        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.vp_frag, container, false);
            user = Logic.getLogic().getUser();
           // TextView textView = (TextView) root.findViewById(R.id.section_label);
            //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));

           lists = (ListView) root.findViewById(R.id.lists);
           addList = (Button) root.findViewById(R.id.addList);

            final ArrayList<Lists> todoLists = user.getListsAsArray();
            adapter = new ListAdapter(getActivity(), todoLists, this);

            lists.setAdapter(adapter);

            addList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDialog();
                }
            });

          /*  lists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String item = todoLists[position].getName();

                    System.out.println(todoLists[1].getName());

                    Toast.makeText(getActivity(), "hej", Toast.LENGTH_LONG).show();
                }
            });

            */


            return root;
        }
        public void showDialog(){
            FragmentManager fm = getActivity().getSupportFragmentManager();
            CreateListFragment fragment = CreateListFragment.newInstance();
            fragment.setTargetFragment(this, 300);
            fragment.show(fm, "Tilføj liste");
        }

        @Override
        public void onFinishEditDialog(String inputText) {
            Lists list = new Lists(inputText);
            list.tasks = new ArrayList<>();
            user.addList(list);
            user.save();
            adapter.notifyDataSetChanged();

        }

        public void deleteDialog(String name, int position){
            FragmentManager fm =  getActivity().getSupportFragmentManager();
            DeleteList fragment = DeleteList.newInstance(name, position);
            fragment.setTargetFragment(this, 300);
            fragment.show(fm, "Slet liste");
        }


        @Override
        public void onFinishDeleteDialog(String name, int position) {
            user.deleteList(position);
            user.save();
            adapter.notifyDataSetChanged();
            Toast.makeText(getActivity(), "Listen '" + name + "' blev slettet", Toast.LENGTH_SHORT).show();
        }

        public void editDialog(String name, int position){
            FragmentManager fm = getActivity().getSupportFragmentManager();
            EditList fragment = EditList.newInstance(name, position);
            fragment.setTargetFragment(this, 300);
            fragment.show(fm, "Redigér liste");
        }

        @Override
        public void onFinishedEditListDialog(String name, int position){
            user.lists.get(position).setName(name);
            user.save();
            adapter.notifyDataSetChanged();
            Toast.makeText(getActivity(), "Listen blev rettet succesfuldt", Toast.LENGTH_SHORT).show();
        }

        public static ListFragment newInstance(int sectionNumber) {

            ListFragment fragment = new ListFragment();
            Bundle args = new Bundle();
            args.putInt("nr", sectionNumber);
            fragment.setArguments(args);

            return fragment;
        }
    }
}
