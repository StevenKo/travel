package com.taiwan.imageload;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.travel.story.R;
import com.travel.story.entity.Site;

public class GridViewCollectSiteAdapter extends BaseAdapter {

    private final Activity         activity;
    private final ArrayList<Site> data;
    private static LayoutInflater  inflater = null;
    public ImageLoader             imageLoader;

    public GridViewCollectSiteAdapter(Activity a, ArrayList<Site> d) {
        activity = a;
        data = d;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader = new ImageLoader(activity.getApplicationContext(), 70);

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
        View vi = convertView;
        // if (convertView == null)
         
        Display display = activity.getWindowManager().getDefaultDisplay();
        int width = display.getWidth(); // deprecated
        int height = display.getHeight(); // deprecated

        if (width > 480) {
            vi = inflater.inflate(R.layout.item_gridview_site, null);
        } else {
            vi = inflater.inflate(R.layout.item_gridview_site_small, null);
        }

        TextView textTitle = (TextView) vi.findViewById(R.id.grid_item_title);
        ImageView image = (ImageView) vi.findViewById(R.id.grid_item_image);
        TextView textStar = (TextView) vi.findViewById(R.id.grid_item_star_text);

        textTitle.setText(data.get(position).getName());
        textStar.setText(Integer.toString(data.get(position).getStarInt())+"顆");
        
        String picUrl = data.get(position).getPic();
        
        if (picUrl == null || picUrl.equals("null") ) {
            image.setImageResource(R.drawable.app_icon);
        } else {
            imageLoader.DisplayImage(picUrl, image);
        }


        return vi;
    }
}
