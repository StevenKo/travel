package com.taiwan.imageload;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.travel.story.R;
import com.travel.story.SiteActivity;
import com.travel.story.entity.Site;

public class GridViewSiteAdapter extends BaseAdapter {

    private final Activity        activity;
    private final ArrayList<Site> data;
    private static LayoutInflater inflater = null;
    public ImageLoader            imageLoader;

    public GridViewSiteAdapter(Activity a, ArrayList<Site> d) {
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

        vi.setClickable(true);
        vi.setFocusable(true);
        vi.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // Toast.makeText(activity, "tt", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(activity, SiteActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("SiteId", data.get(position).getId());
                bundle.putString("SiteName", data.get(position).getName());
                intent.putExtras(bundle);
                activity.startActivity(intent);

            }

        });

        TextView textTitle = (TextView) vi.findViewById(R.id.grid_item_title);
        ImageView image = (ImageView) vi.findViewById(R.id.grid_item_image);
        TextView textStar = (TextView) vi.findViewById(R.id.grid_item_star_text);

        textTitle.setText(data.get(position).getName());
        textStar.setText(Integer.toString(data.get(position).getStarInt()) + "顆");

        String picUrl = data.get(position).getPic();

        if (picUrl == null || picUrl.equals("null") || picUrl.equals("http://destres1.c-ctrip.com/img/golbal/default_view_img80x80.png")) {
            image.setImageResource(R.drawable.app_icon);
        } else {
            imageLoader.DisplayImage(picUrl, image);
        }

        return vi;
    }
}
