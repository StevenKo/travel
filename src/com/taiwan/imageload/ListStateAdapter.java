package com.taiwan.imageload;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.travel.story.R;
import com.travel.story.entity.State;

public class ListStateAdapter extends BaseAdapter {

    private final Activity            activity;
//    private final ArrayList<String> data;
    private final ArrayList<State> data;
    private final Integer[] images;
    private static LayoutInflater     inflater = null;

    public ListStateAdapter(Activity a,    ArrayList<State> d, Integer[] t) {
        activity = a;
        data = d;
        images = t;
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

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.item_list, null);
	        ImageView image = (ImageView) vi.findViewById(R.id.image_list);
	        TextView text = (TextView) vi.findViewById(R.id.text_list);
	        text.setText(data.get(position).getName());
        
        try{
        	image.setImageResource(images[position]);
        }catch(Exception e){
        	image.setVisibility(View.GONE);
        }
        
        return vi;
    }
}