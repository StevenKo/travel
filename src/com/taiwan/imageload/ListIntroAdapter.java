package com.taiwan.imageload;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.travel.story.AreaIntroActivity;
import com.travel.story.LastCategoryActivity;
import com.travel.story.R;
import com.travel.story.entity.Area;

public class ListIntroAdapter extends BaseAdapter {

    private final Activity            activity;
    private ArrayList<Area> data;
    private static LayoutInflater     inflater = null;

    public ListIntroAdapter(Activity a, ArrayList<Area> d) {
        activity = a;
        data = d;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View vi = inflater.inflate(R.layout.item_list_section_row_intro, null);
        
        TextView title = (TextView) vi.findViewById(R.id.row_title);
		title.setText(data.get(position).getName());
		
		vi.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                
            	Intent intent = new Intent(activity, LastCategoryActivity.class);
        		Bundle bundle = new Bundle();
        		bundle.putInt("AreaId", data.get(position).getId()); 
        		bundle.putString("AreaName", data.get(position).getName());
        		intent.putExtras(bundle);
        		activity.startActivity(intent);

            }

        });
		
		
		
		TextView title_intro = (TextView) vi.findViewById(R.id.row_intro);
		title_intro.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // Toast.makeText(activity, "tt", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(activity, AreaIntroActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("TypeId", 1); // 0 for nation, 1 for area
                bundle.putInt("AreaId", data.get(position).getId());
                bundle.putString("AreaName", data.get(position).getName());
                intent.putExtras(bundle);
                activity.startActivity(intent);

            }

        });
        
        return vi;
    }
}
