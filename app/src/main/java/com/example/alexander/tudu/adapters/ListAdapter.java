package com.example.alexander.tudu.adapters;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alexander.tudu.FrontpageFragment;
import com.example.alexander.tudu.MainActivity;
import com.example.alexander.tudu.R;
import com.example.alexander.tudu.VPFragment;
import com.example.alexander.tudu.logic.Lists;

/**
 * Created by Alexander on 03-04-2016.
 */
public class ListAdapter extends BaseAdapter{

    private Context context;
    private Lists[] todoLists;

    public ListAdapter(Context ctx, Lists[] list) {
        context = ctx;
        todoLists = list;
    }


    @Override
    public int getCount() {
        return todoLists.length;
    }

    @Override
    public Object getItem(int position) {
        return todoLists[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        TextView title, date, task_nr;

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View list;

        if (convertView == null) {
            list = inflater.inflate(R.layout.list_item_lists, null);

            list.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Fragment newFragment = new FrontpageFragment();
                   if (newFragment != null) {
                       switchFragment(newFragment);
                   }

                    Toast.makeText(context, todoLists[position].getName(), Toast.LENGTH_LONG).show();
                    //Toast.makeText(context,"you clicked item: "+rowID, Toast.LENGTH_LONG.show();
                    //code you want to execute on click of list item...
                }
            });


        } else {
            list = convertView;
        }

        title = (TextView) list.findViewById(R.id.list_title);
        title.setText(todoLists[position].getName());

        date = (TextView) list.findViewById(R.id.list_date);

        task_nr = (TextView) list.findViewById(R.id.list_task_nr);

        return list;
    }

    private void switchFragment(Fragment newFragment) {
        if (context == null) {
            return;
        }
        if (context instanceof MainActivity) {
            MainActivity main = (MainActivity) context;
            main.newFragment(newFragment);
        }
    }
}
